# Published APIs
Goat rodeo published a set of APIs for components to consume. Goat Rodeo itself publishes a built-in component which is responsible for exposing these APIs to other components. This document is meant to be *conceptual* documentation to describe what is available and how it is intended to be used. Individual types and their members are documented in the reference documentation.

## Logging
In the namespace `io.spicelabs.rodeocomponents.APIS.logging` there is an interface called `RodeoLogger` which defines a mechanism that components can use to log information while they're running. This is a common need in programs that may run for a long time and might be difficult to track without logging. Having the tools provided by Goat Rodeo is meant to ensure that components can have this tool without having to bring in a (possibly conflicting) dependency.

The main calls are all similar in that they provide a way of reporting text. What differs is the circumstances or categorization of that information:
- error - report an error with an optional exception
- warn - report a warning
- info - report information
- debug - report debug only information - favor info over debug

Note that it is not necessary to include your component's identity as that is automatically included in the reported information.

## Argument Handling
Argument handling presents an interesting chicken-and-the-egg challenge in that there may be command line arguments which affect the loading of components, but there will also be command line arguments that need to be processed by components. Also components need to go through the component loading process of initialization, exportation, and importation before they can receive arguments. To this end, the process that Goat Rodeo uses is:
1. Process all command line arguments except for those needed by components
2. Accumulate all command line arguments for components
3. Discover components
4. Initialize components
5. Call components to export APIs
6. Call components to import APIs
7. Call the components that have registered to listen for command line arguments
8. Call components to complete loading

Because of this ordering, a component that wants to consume command line arguments be prepared to subscribe to the argument handling API in the import step and also register as an argument listener in the same step. **If a component does not register as a command line argument listener in the same step it will never receive command line arguments.**

When the ordering is done this way, a component will receive configuration information *before* the loading complete step. This means that final configuration should happen in the loading complete step.

### Note on Component Naming
Arguments syntax is currently `--component componentame[,param]+` and argument parsing is based on white space between command line arguments. This means that if you have a component that has white space in its name, you will have some challenges getting your arguments. Similarly, if you name your component with "unusual" characters such as emoji, you will be creating a situation where it may be ornerous to pass command line arguments to your component.

### Note on Parameter Parsing
Today, individual arguments for components are comma separated with no white space (although this may change in the future). Components will receive their parameters without the commas and as a `List` of individual `String` objects.

## Package URL Generation
Some APIs within goat rodeo require package URLs. In order to facilitate this, the `PurlAPI` provides an abstract definition of a package URL and a factory for generating them from the components. The factory provides a fluent interface for with all the `with...` methods return a (possibly) new instance of the factory. After assembling all the elements that are required, call the `toPurl` method to generate a package URL which will contain the elements.

Typical usage can be seen in this example:
```java
public Purl makePurl(PurlAPI api, String type, String name, String namespace) throws MalformedURLException
{
    return api.newPurlFactory().withType(type).withName(name).with(namespace).toPurl();
}

```

See [the purl specification](https://github.com/package-url/purl-spec?tab=readme-ov-file) for details on the meanings of the various elements and which are required and optional.
