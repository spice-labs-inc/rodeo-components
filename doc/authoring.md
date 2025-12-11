# Authoring a Component

The main interfacing for the Goat Rodeo Component Model is written in Java, so any JVM language that can implement Java interfaces and consume implementations of Java interfaces can be used to create a Component. 

In order for your component to be visible to Goat Rodeo, you must do the following:

- Create a reference in your project to RodeoComponents **TODO - add a link to the maven repository**
- Create one or more classes that implement the interface `RodeoComponent`
- Advertise your components in your resources (see below)
- Compile and package your code into a JAR file


## Implementing RodeoComponent

First create a public class that extends `RodeoComponent`

```java
package com.yourcompany.components;
import io.spicelabs.rodeocomponents.*;
import java.lang.Runtime.Version;

public class MyComponent extends RodeoComponent {
  public MyComponent() { }
  public void initialize() throws Exception { }
  public RodeoIdentity getIdentity() { /* TODO */ }
  public Version getComponentVersion() { /* TODO */ }
  public void exportAPIFactories(APIFactoryReceiver receiver) { }
  public void importAPIFactories(APIFactorySource factorySource) { }
  public void onLoadingComplete() { }
  public void shutDown() { }
}
```

Next stub out the methods. For `getIdentity`, you will need a class that implements `RodeoIdentity`. You can create a separate class that implements this if you want. Sometimes for build automation this is easier, but for expedience, you can simply implement `RodeoIdentity` in the same class that implements `RodeoComponent`

```java
package com.yourcompany.components;
import io.spicelabs.rodeocomponents.*;
import java.lang.Runtime.Version;

// simple implementation of a component

public class MyComponent extends RodeoComponent, RodeoIdentity {
  private static final _name = "MyComponent"
  private static final _publisher = "YourCompany"
  public MyComponent() { }
  
  public void initialize() throws Exception { }

  // RodeoIdentity implementation
  public String name() { return _name; }
  public String publisher() { return _publisher; }

  public RodeoIdentity getIdentity() { return this; }
  public Version getComponentVersion() { return RodeoEnvironment.currentVersion(); }
  public void exportAPIFactories(APIFactoryReceiver receiver) { }
  public void importAPIFactories(APIFactorySource factorySource) { }
  public void onLoadingComplete() { }
  public void shutDown() { }
}
```

Finally, you need to create a file in your `resources` directory in the subdirectory `META-INF/services` named `io.spicelabs.rodeocomponents.RodeoComponent`. This is a text file that is a list of all types that implement `RodeoComponent`. Each line should be the fully qualified name of your component:
```
com.yourcompany.components.MyComponent
```


## Importing APIs From Your Component

In order to do anything useful as a Component of Goat Rodeo, you will need to do some work in `importAPIFactories`. For each API you want to import, you will need to call the `factorySource` method `getAPIFactory`. This method takes the name of the API you want to import, an instance of the `RodeoComponent` importing the factory, and the expected type of the API. After importing the factory, the Component will need to call the factory to get the actual API.

Each API should have a class that defines the name of the API that should be passed to `factorySource`.

Here's an example of how to do that:

```java
public class MyComponent extends RodeoComponent, RodeoIdentity {
 
  APIFactory<RodeoLogger> loggerFactory;
  RodeoLogger logger;

  public void importAPIFactories(APIFactorySource factorySource) {
    Optional<APIFactory<RodeoLogger>> opt = factorySource.getAPIFactory(
          RodeoLoggerConstants.NAME, this, APIFactory<RodeoLogger>.class);
    if (!opt.isPresent())
        return;
    loggerFactory = opt.get();
    logger = loggerFactory.buildAPI();
  }

  public void onLoadingComplete() {
    logger.info("MyComponent has been loaded.")
  }
}
```

## Exporting Your Own APIs

To export an API from your component, you will need to do several things:

1. Define an interface for your API
2. Define a constant name for your API
3. Publish your API in its own JAR*
4. Write a class that implements your API
5. Write a class that implements `APIFactory` specialized to your API type
6. When your Component's `exportAPIFactories` method is called, call the receiver with your `APIFactory`

*This is not strictly necessary, but if you want other Components to have access to your API's, it's cleaner to have the your Component and other Components depend on the same JAR instead of requiring other Components to depend on your Component's JAR.

### Defining an Interface For Your API

This is done by creating a Java interface that exposes the functionality that you want and implements the interface `API`. Here is an example of how to do that:

```java
public interface MyGreeter extends API { // define the interface
  void greet();
}

public class MyGreeterConstants { // define the name
  public static final NAME = "MyComponentGreeter";
}
```

### Implementing Your API

This is as simple as creating a class that implements your interface:

```java
public class MyGreeterImpl extends MyGreeter {
  private String _owner;
  public MyGreeterImpl(String owner) {
    _owner = owner;
  }
  public void greet() {
    System.out.println("Greetings from " + _owner + "!");
  }

  public void release() { }
}
```

### Implementing Your Factory

To write your factory, you need to create a class that implements the `APIFactory<T>` where T is your API *interface*:

```java
public class MyGreeterFactory extends APIFactory<MyGreeter> {
  public MyGreeterFactory() { }
  public String name() { return MyGreeterConstants.NAME; }
  public MyGreeter buildAPI(RodeoComponent subscriber) {
    return new MyGreeterImpl(subscriber.getIdentity().name());
  }
}
```

Note how that API factory is able to customize the API to the subscriber. This is not mandatory, but can be useful to track which Components are using your API.

### Publishing Your API Factory

When your Component has its `exportAPIFactories` method called, your component can expose it's API factory by calling the `publishFactory` method for each API factory that it exports.

Here is a simple example that demonstrates that:

```java
public class MyComponent extends RodeoComponent {
  public void exportAPIFactories(APIFactoryReceiver receiver) {
    MyGreeterFactory factory = MyGreeterFactory();
    // Note: the class that gets passed in is the class of the API interface NOT the factory
    receiver.publishFactory(this, factory.name(), factory, MyGreeter.class);
  }
}
```

In a real-world situation, however, a Component should track each `APIFactory` and each `API` that has been built. This way, if a Component has a failure, it can deactivate the factories and the APIs so that no damage will result.

### Cleaning Up

There are several forms of clean-up that may happen in the lifetime of a Component:

- Short-term clean-up
- Failure clean-up
- End-of-life clean-up

Short-term clean-up is when a resource, such as an API, has been acquired and can be released after processing is complete. Short-term, in this case, means over the span of a single call. To clean up under these circumstances is either the choice of the Component author or may be dictated by the resource itself.

Failure is when in the course of events, an error has occurred from which the Component can't recover. Under these circumstances, the Component is responsible for not only cleaning up, but ensuring that if the Component has exported API factories that neither the factories nor any APIs will cause damage. Under these circumstances, the Component should call release on each API is has imported.

When a Goat Rodeo completes, it will call the `shutDown` method of every Component. At this time, the Component should call `release` on every API that it has acquired. Since the order of calling `shutDown` is undefined, a Component should be prepared to gracefully handle `release` calls to APIs that it has published even after `shutDown` has been called. There is one exception to this, since the `RodeoLogger` API is the only provided mechanism for Components to generate information, every Component can safely assume that the once acquired, the logging API will always be available to other Components.