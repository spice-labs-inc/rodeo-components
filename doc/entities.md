# Component Model Entities

The Goat Rodeo Component Model is based on the 4 entities

- Host
- Component(s)
- API Factories
- APIs

## Host

The host is a class which carries several responsibilities:
- Loading components
- Aggregating components
- Proving basic services for components
- Handling errors

## Components

A component is a class that meets the interface `RodeoComponent`. It performs the following actions when requested:
- Initialization
- Identification
- Reporting their version
- Exporting 0 or more API factories
- Importing 0 or more API factories
- Shutting down

## API Factories

An API Factory is a class that is used for constructing APIs for use by either the host or other components. The pattern of using a factory instead of a singleton API instance allows the API publisher to tailor an API to a particular subscriber. The factory pattern allows a component to return a singleton API if it so desires.

## APIs

An API a logical collection of operations that are represented by a Java interface. An API can provide functionality that can be used by any of the entities, although it is most likely to be used by either the host or other components. There is no reason why the host wouldn't provide its own component or components for publishing "standard" APIs.

## Notes

- It is possible for a class to simultaneously be a Component, an API Factory, and an API. For simple components that only do one thing, this is reasonable, but if a Component needs to publish multiple API Factories, the API Factories should be separate classes.

- It is possible for an API Factory to also be its own API. For API Factories that only need a singleton API, this is reasonable, but if an API Factory needs to construct a new API for each request, it's probably easier to have separate API Factory and the API classes.
