# Component Lifetime

As an author of a Component, it's important to understand the lifetime of a Component and what assumptions you can make based on contracts that will be enforced by the Host.

## Discovery and Loading

The Host will employ a mechanism to discover Components and will load them. This mechanism is currently using `java.util.ServiceLoader`, but Component authors should make no assumptions about how the Component will be loaded. What is important is that the component *must* implement the interface `io.spicelabs.rodeocomponents.RodeoComponent` as this is what will be used for discovery. In addition, the class that implements `RodeoComponent` *must* have a public constructor with no arguments. The constructor should do as little as possible. The constructor *must* not throw exceptions as the handling of the exception is dependent on the mechanism that loads it.

The interface `RodeoComponent` has no methods in it. Instead, it will always inherit from another interface which contains the current version of methods. Component authods should always implement `RodeoComponent` rather than the parent interface.

After being discovered, Components methods will be called in a particular order. The Host may choose to disregard Components that are historically problematic.

Components need to be aware that they co-exist with other components and *must not* depend on the order of when each Component is called in relation to each other. Unless Component ordering is explicitly stated, it should never be assumed as that is an implmentation detail which is subject to change. For example, if the `RodeoComponent` interface is augmented at some point, any prior version of Components may be put into an adapter for compatability or may be put into a separate collection.

## Initialization

After all the Components have been discovered and loaded, the `initialize` method will be called by the Host. This is an opportunity for the Component to do any work in preparation for later stages. For example, if a Component requires resources that need to be loaded into memory, `initialize` is a perfect time to do this. It is also an opportunity for the Component to "fail fast". For example, if the Component has external dependencies that aren't present, the Component should throw an `Exception`. The Host will catch the exception, report the message from the exception, and then the Host will call the `shutDown` method and discard and disregard the Component.

After calling initialize, the Host will call `getComponentVersion`. This is to determine if the what version of the `RodeoComponent` interface that the Component was compiled against. If this version can't be supported, the Host will call the `shutDown` method and discard and disregard the Component.

Components *must not* call their own `initialize` method or the `initialize` method of other Components. Components may call `getComponentVersion`.

## Identification

The Host will call the `getIdentity` method of the Components. This is intended to be used for information in log files. This method may be called at any point in the life of the Component and Components are free to call it as well.

## Exporting

The Host will call the `exportAPIFactories` method of each Component. This method is the opportunity for a Component to publish API factories for eith the Host or other components to use. Components *must not* invoke this method. Not every Component will export API factories. For the most part, this is for components that have elements that they want to share with other Components. For example, a suite of related Components may want to share common code or dependencies.

## Importing

The Host will call the `importAPIFactories` method of each Component. This method is the opportunity for a Component to subscribe to API factories provided by the Host or by other Components. Importing will always happen after exporting. Components *must not* invoke this method.

## Completion

After all the Components have gone through the prior steps, the Host will invoke the `onLoadingComplete` method. This provides the component with an opportunity to perform extra work or to inject event listeners using imported API factories. Components *must not* invoke this method.

## Shutting Down

Either at the completion of work done by the Host or when a fatal error has happened caused by a Component, the Component's `shutDown` method will be invoked by the Host. This is an opportunity for the Component to release any acquired resources and to clean up. This method should be written defensively - the Component may not be able to guarantee that it is in a consistent state when this method is called. If a component has exported API factories, it *must not* respond to any API that it provides. Components *must not* invoke any methods in APIs to which it has subscribed. Components *must not* invoke this method.