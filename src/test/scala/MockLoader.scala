package io.spicelabs.components.testing

import io.spicelabs.rodeocomponents.RodeoComponent
import java.util.ServiceLoader
import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._
import io.spicelabs.rodeocomponents.RodeoEnvironment
import io.spicelabs.rodeocomponents.APIFactoryReceiver
import io.spicelabs.rodeocomponents.APIFactorySource
import io.spicelabs.rodeocomponents.APIFactory
import io.spicelabs.rodeocomponents.API
import java.{util => ju}
import scala.collection.mutable.HashMap

private case class PublishedAPIFactory[T <: API](component: RodeoComponent, apiFactory: APIFactory[T], factoryType: Class[T])

class MockLoader(identityPattern: String) extends APIFactoryReceiver, APIFactorySource {
    val componentMatcher = identityPattern.r
    val components: ArrayBuffer[RodeoComponent] = ArrayBuffer()
    val badComponents: ArrayBuffer[RodeoComponent] = ArrayBuffer()

    val exportedAPIs: HashMap[String, PublishedAPIFactory[?]] = HashMap()

    def loadComponents() = {
        val loader = ServiceLoader.load(classOf[RodeoComponent])
        var members = List[RodeoComponent]()
        for el <- loader.iterator().asScala do {
            try {
                el.initialize()
                if (regexMatches(el)) {
                    if (elementIsValid(el)) {
                        components.addOne(el)
                    } else {
                        badComponents.addOne(el)
                    }
                }
            } catch {
                case err: Exception => {
                    val message = err.getMessage()
                    ()
                }
            }
        }
    }

    def componentPublishAPIFactories() = {
        for comp <- components do {
            try {
                comp.exportAPIFactories(this)
            } catch {
                case err: Exception => ()
            }
        }
    }

    def componentImportAPIFactories() = {
        for comp <- components do {
            try {
                comp.importAPIFactories(this)
            } catch {
                case err: Exception => ()
            }
        }
    }

    private def regexMatches(el: RodeoComponent): Boolean = {
        val identity = el.getIdentity()
        if (identity == null)
            return false
        componentMatcher.matches(identity.name())
    }
  
    private def elementIsValid(el: RodeoComponent): Boolean = {
        if (el.getComponentVersion() != RodeoEnvironment.currentVersion())
            return false
        return true    
    }

    override def publishFactory[T <: API](publisher: RodeoComponent, apiName: String, apiFactory: APIFactory[T], apiType: Class[T]): Unit = {
        exportedAPIs += (apiName -> PublishedAPIFactory(publisher, apiFactory, apiType))
    }

    override def getAPIFactory[T <: API](name: String, subscriber: RodeoComponent, factoryType: Class[T]): ju.Optional[APIFactory[T]] = {
        val factoryOpt = exportedAPIs.get(name)

        factoryOpt match
            case Some(factory) => {
                if (factory.factoryType != factoryType)
                    ju.Optional.empty()
                else
                    ju.Optional.of(factory.apiFactory.asInstanceOf[APIFactory[T]])
            }
            case None => ju.Optional.empty()
    }

}
