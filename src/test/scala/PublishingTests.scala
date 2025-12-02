package io.spicelabs.components.testing

import io.spicelabs.rodeocomponents.APIFactory
import io.spicelabs.rodeocomponents.API
import io.spicelabs.rodeocomponents.APIFactoryReceiver
import io.spicelabs.rodeocomponents.RodeoIdentity
import java.lang.Runtime.Version
import io.spicelabs.rodeocomponents.RodeoEnvironment
import scala.compiletime.ops.double
import io.spicelabs.rodeocomponents.APIFactorySource
import scala.jdk.OptionConverters._

trait Reporter extends API {
    def report(message: String): Unit
}

class FooReporter(reportFunc: (String) => Unit) extends Reporter {
    override def report(message: String): Unit = {
        reportFunc(message)
    }
    override def release(): Unit = { }
}

class FooReporterFactory(fr: FooReporter) extends APIFactory[Reporter] {
    override def name(): String = "foo-reporter"
    override def buildAPI(): Reporter = fr
}

trait Worker extends API {
    def work(): Unit
}

class FooWorker extends Worker {
    var reporter: Option[Reporter] = None
    override def work() = {
        reporter.foreach(r => r.report(("did it")))
    }
    override def release(): Unit = { }
}

class FooWorkerFactory(fw: FooWorker) extends APIFactory[Worker] {
    override def name(): String = "foo-worker"
    override def buildAPI(): Worker = fw
}

class FooReporterComponent extends MockComponent {
    var lastReport = ""
    val reporter = FooReporter((s) => {
        lastReport = s
    })

    val worker = FooWorker()

    override def getIdentity(): RodeoIdentity = MockIdentity("publishing-test")
    override def getComponentVersion(): Version = RodeoEnvironment.currentVersion()
    override def exportAPIFactories(receiver: APIFactoryReceiver): Unit = {
        receiver.publishFactory(this, "Reporter", FooReporterFactory(reporter), classOf[Reporter])
        receiver.publishFactory(this, "Worker", FooWorkerFactory(worker), classOf[Worker])
    }

    override def importAPIFactories(factorySource: APIFactorySource): Unit = {
        val reporterFactory = factorySource.getAPIFactory[Reporter]("Reporter", this, classOf[Reporter]).toScala
        if (reporterFactory.isDefined) {
            worker.reporter = Some(reporterFactory.get.buildAPI())
        }
    }
}

class PublishingTests extends munit.FunSuite {
    test("loads-publisher") {
        val loader = MockLoader("publishing\\-test")
        loader.loadComponents()
        assertEquals(loader.badComponents.length, 0)
        assertEquals(loader.components.length, 1)       
    }

    test("publish-import") {
        val loader = MockLoader("publishing\\-test")
        loader.loadComponents()
        loader.componentPublishAPIFactories()
        loader.componentImportAPIFactories()


        val fooReporterComponent = loader.components(0).asInstanceOf[FooReporterComponent]

        val workerFactoryOpt = loader.getAPIFactory[Worker]("Worker", null, classOf[Worker])
        val workerFactory = workerFactoryOpt.get()
        val localWorker = workerFactory.buildAPI()

        localWorker.work()

        assertEquals(fooReporterComponent.lastReport, "did it")

    }
}
