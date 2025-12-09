package io.spicelabs.components.testing

import io.spicelabs.rodeocomponents.*

import java.lang.Runtime.Version
import java.util.ServiceLoader
import io.spicelabs.rodeocomponents.APIS.RodeoLogger

class MockLogger(report: (String, String) => Unit) extends RodeoLogger {
    override def debug(message: String): Unit = {
        report("debug", message)
    }
    override def error(message: String): Unit = {
        report("error", message)
    }
    override def error(message: String, cause: Throwable): Unit = {
        report("error", message)
    }
    override def info(message: String): Unit = {
        report("info", message)
    }
    override def warn(message: String): Unit = {
        report("warn", message)
    }
    override def release(): Unit = { }
}

class MockLoggerFactory(ml: MockLogger) extends APIFactory[RodeoLogger] {
    override def name(): String = "MockLogger"
    override def buildAPI(subscriber: RodeoComponent): RodeoLogger = ml
}

class LoggingClass extends MockComponent {
    var lastLevel = ""
    var lastMessage = ""
    val logger = MockLogger((level, message) => {
        lastLevel = level
        lastMessage = message
    })
    override def getIdentity(): RodeoIdentity = MockIdentity("logging-test")
    override def getComponentVersion(): Version = RodeoEnvironment.currentVersion()
    override def exportAPIFactories(receiver: APIFactoryReceiver): Unit = {
        receiver.publishFactory(this, "MockLogger", MockLoggerFactory(logger), classOf[RodeoLogger])
    }
}


class LoggingTest  extends munit.FunSuite {
    test("loads-logger") {
        val loader = MockLoader("logging\\-test")
        loader.loadComponents()
        assertEquals(loader.badComponents.length, 0)
        assertEquals(loader.components.length, 1)       
    }

    test("published-api") {
        val loader = MockLoader("logging\\-test")
        loader.loadComponents()
        loader.componentPublishAPIFactories()
        assertEquals(loader.exportedAPIs.size, 1)
        assert(loader.exportedAPIs.contains("MockLogger"))
    }

    test("api-works") {
        val loader = MockLoader("logging\\-test")
        loader.loadComponents()
        loader.componentPublishAPIFactories()

        val loggerComponent = loader.components(0).asInstanceOf[LoggingClass]

        val loggerFactoryOpt = loader.getAPIFactory[RodeoLogger]("MockLogger", null, classOf[RodeoLogger])
        val loggerFactory = loggerFactoryOpt.get()
        val logger = loggerFactory.buildAPI(loggerComponent)

        logger.debug("foo")
        assertEquals(loggerComponent.lastLevel, "debug")
        assertEquals(loggerComponent.lastMessage, "foo")

        logger.error("bar")
        assertEquals(loggerComponent.lastLevel, "error")
        assertEquals(loggerComponent.lastMessage, "bar")

        logger.warn("baz")
        assertEquals(loggerComponent.lastLevel, "warn")
        assertEquals(loggerComponent.lastMessage, "baz")

        logger.info("splunge")
        assertEquals(loggerComponent.lastLevel, "info")
        assertEquals(loggerComponent.lastMessage, "splunge")
    }
}
