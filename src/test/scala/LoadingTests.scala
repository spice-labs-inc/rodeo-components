package io.spicelabs.components.testing

import io.spicelabs.rodeocomponents.*

import java.lang.Runtime.Version
import java.util.ServiceLoader


class GoodClass extends MockComponent {
    override def getIdentity(): RodeoIdentity = MockIdentity("loadingtest-good")
    override def getComponentVersion(): Version = RodeoEnvironment.currentVersion()
}

class BadVersionClass extends MockComponent {
    override def getIdentity(): RodeoIdentity = MockIdentity("loadingtest-bad-version")
    override def getComponentVersion(): Version = Version.parse("800.0.0.1")
}

class LoadingTests extends munit.FunSuite {
    test("good-and-bad") {
        val loader = MockLoader("loadingtest.*")
        loader.loadComponents()
        assertEquals(loader.badComponents.length, 1)
        assertEquals(loader.components.length, 1)
    }
}
