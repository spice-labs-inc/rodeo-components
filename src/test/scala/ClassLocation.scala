package io.spicelabs.components.testing

import io.spicelabs.rodeocomponents.*

import java.lang.Runtime.Version
import java.util.ServiceLoader

import scala.jdk.CollectionConverters._

class MockIdentity(_name: String) extends RodeoIdentity {
    override def name(): String = _name
    override def publisher(): String = "Spice Labs"
}

class MockComponent extends RodeoComponent {
    override def initialize(): Unit = { }
    override def exportAPIFactories(receiver: APIFactoryReceiver): Unit = { }
    override def importAPIFactories(factorySource: APIFactorySource): Unit = { }
    override def shutDown(): Unit = { }
    override def getComponentVersion(): Version = Version.parse("0.0.0")
    override def getIdentity(): RodeoIdentity = MockIdentity("Mock Component")
}

class LocateClass extends munit.FunSuite {
    test("has-a-class") {
        val loader = ServiceLoader.load(classOf[RodeoComponent])
        var members = List[RodeoComponent]()
        for el <- loader.iterator().asScala do {
            if (el.getClass().getName().contains("MockComponent")) {
                members = members :+ el
            }
        }
        assertEquals(1, members.length)
        val component = members(0)
        assertEquals(component.getIdentity().name(), "Mock Component")
    }
}

