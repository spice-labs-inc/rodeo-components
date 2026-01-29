# Rodeo Components

Rodeo Components is a suite of interfaces and small number of classes that are used for formally importing or exporting Application Programming Interfaces (APIs) from Goat Rodeo.

An API is a set of related operations grouped in a Java interface. Goat Rodeo acts as a host for Rodeo Components and has a mechanism for discovering components and a process for managing the lifetime of a component (see [here](component-life.md) for more information).

While Goat Rodeo is written in scala, Rodeo Components is exposed as a series of Java interfaces and classes so it should be accessibly in any JVM based language which can consume Java types.

## General Style
APIs are considered to be isolated islands of functionality that live within their own namespace only as they relate to the task which they accomplish. For example, the API for logging exists in the namespace `io.spicelabs.rodeocomponents.logging`.

APIs declared in Rodeo Components are only ever published by Goat Rodeo. Components may publish their own APIs but they will not be included here.

APIs should expose as few new dependencies as possible. Dependencies are considered an implementation detail and since APIs are published as interfaces, avoid including types that require new dependencies. This can be accomplished in a number of ways, but typically this is done by having an interface for a data type exposed as part of the API and a separate implementation which implements that interface typically as an adapter or fly-weight version of the type.

## No Nulls - Never Had Them, Never Will

In short, you won't get `null` from us; we won't accept `null` from you.

[Tony Hoare](https://en.wikipedia.org/wiki/Tony_Hoare) referred to his creation of `null` in 1965 as a billion dollar mistake. This is carried through into Java. In APIs from Goat Rodeo, any type that falls into the category of either having a value or having no value will be represented as an `Optional<T>` type. It is **never** correct to call a method with a `null` parameter and no method will return a `null`. Absent a widely accepted mechanism to label method parameters and return values, we will accept "no nulls" as a convention.

## API Factories

One thing that is noticeable is that there is no way to get an API from the component system directly. This approach requires components to import a factory to build an API. This gives an API publisher choices in how the factory and API will operate.  For example, simple APIs could be a singleton. Other APIs may only operate once and need to be rebuilt. Factories can also customize APIs for a particular component which uses it. For example, the logging API builds a logger that contains information about the subscriber so that logging messages include that information.