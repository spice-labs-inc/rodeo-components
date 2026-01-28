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

## Mime Identification API
When Goat Rodeo scans files, it tries to identify the artifacts and produce a [MIME type](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/MIME_types). One of the main use cases for an extension to Goat Rodeo is to identify and subsequently handle files that aren't handled "out of the box". A component will register a MIME type identifier with Goat Rodeo and it will be called to examine the artifacts. There are two types of
identifiers: one that operates on `InputStream` objects and one that operates on `FileInputStream` objects.

The distinction between them is important: Goat Rodeo operates on InputStreams but in many cases those streams exist entirely in memory or are part of a system that can't effectively back up (like tar files). Creating a temporary file for a particular InputStream will be far more expensive than operating on a memory stream. As such, components are *discouraged* from using `MimeFileStreamIdentifier`. The main reason for
needing a `MimeFileStreamIdentifier` is when identifying or working on an artifact it is necessary to seek around the stream to verify that the identity.

As such, the MIME identification system is designed to avoid having to provide a `FileInputStream` if possible. This is done in 3 stages:
- `preferredHeaderLength` - Identifiers get called to report how many bytes they need to identify a file
- `canHandleHeader` - Identifiers get handed a byte array to check if the header might *possibly* be something they can identify
- `identifyMimeType` - Identifiers get handed a stream the mime identified so far and has an opportunity to positively identify the stream

The reason behind this is that the MIME identification system avoids creating a temporary file and gives MIME identifiers to select
themselves *out* of the process. Further, by asking each identifier for a header length, the system can read a header once for all identifiers.
The system maintains separate collections for `InputStream` based identifiers and `FileInputStream` identifiers. From the set of `InputStream` identifiers that have returned `true` for `canHandleHeader`, if then any positively identify the stream via `identifyMimeType` then no `FileInputStream` based identifier will get called to identify the file and no temporary file will be created. Therefore you should be aware that under all circumstances `InputStream` based identifiers will be given deference.

### preferredHeaderLength
A component should report how many bytes it would like for examining a header. For simple file types, this is going to be a constant value. For example, a PDF document should start with `%!PDF-`, so in theory a component would report `6`, but the PDF specification says that that can appear anywhere in the first kilobyte of the file, so a correct detector would return `1024`.

If a component cannot possibly identify a file based on a header, it should return `MimeConstants.UNKNOWN_BYTES_NEEDED`. If a component returns this, it will still get called with `canHandleHeader`.

### canHandleHeader
When a component's `canHandleHeader` is called, the array passed in will either contain the maximum number of bytes asked for by all the identifiers or all the bytes in the stream *whichever is smaller*. Therefore a component **can not** assume that it will get the number of bytes that it asked for.

When `canHandleHeader` is called, a component should return `true` if there is a *possibility* that the header might be something that it can
identify. In other words, components are encouraged to return a true positive or a false positive. If a component returns `false` it will not be called to identify the file.  As an example where a component would return a (potentially) false positive, when looking at a .NET assembly a
true positive can only be achieved by seeking around in the file, but there are a number of header bytes that indicates that the file *might* be
a .NET assembly as it is an extension of PE32 files. So if file has a PE32 header, it *might* be a .NET assembly, so it would return true.

If an identifier has returned `UNKNOWN_BYTES_NEEDED` in `preferredHeaderLength` it will probably want to return `true` for `canHandleHeader` whether or not it actually examines the header.


### identifyMimeType
For each class of MIME identifier that has returned `true` in `canHandleHeader`, each will be called in turn to identify the mime type of the content. Each subsequent identifier will be passed the last MIME type identified or `application/octet-stream` if no MIME type has been identified. The MIME identification process will return the last non-octet stream that was identified. Keeping this in mind, if an identifier gets passed a MIME type identified by a previous identifier, it should avoid returning a new MIME type unless it is a specialization of the previous one or if it can tell for sure that the previous identification was either incomplete or incorrect. As noted before, identifiers of MIME types that operate on an `InputStream` will be given preference over those that operate on a `FileInputStream`. When an identifier has positively identified content, it should return `Optional.of(yourMimeTypeHere)`. If it can't identify the content or it feels that the previous MIME type is sufficient, it should return `Optional.empty()`.

### Notes
- For `MimeInputStreamIdentifier` objects the stream that is passed in will be marked for it and reset afterwards. There is no need to call either `mark` or `reset` and in fact doing so will cause an error.
For `MimeFileInputStreamIdentifier` objects, the stream will be positioned at the start of the stream and restored afterwards.
- MIME identifiers are responsible for handling exceptions that may be thrown in the course of `identifyMimeType`
- Where possible, try to use [existing MIME types](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/MIME_types/Common_types) and if you are identifying something that isn't an existing MIME type, consider using either the vanity tree or unregistered tree (see [here](https://en.wikipedia.org/wiki/Media_type#Subtypes) for more detail).
- If you are identifying an extension on an existing type, consider using the `+` suffix or the parameter field.
- Illegal MIME types returned by identifiers will be ignored.

### A Simple Example

This is a simple example of a MIME identifier that can identify Adobe PostScript files that have been adorned with the appropriate header.

```java
final class PSIdentifier extends MimeInputStreamIdentifier {
    private static final String header = "%!ps-adobe";
    @Override
    public int preferredHeaderLength() { return header.length(); }
    @Override
    public boolean canHandleHeader(byte[] data) {
        return hasPSHeader(data);
    }
    @Override
    public Optional<String> identifyMimeType(InputStream is, String mimeSoFar) {
        try {
            byte[] data = is.readNBytes(header.length());
            if (hasPSHeader(data)) {
                return mimeSoFar == "application/octet-stream" ? Optional.of("application/postscript") : Optional.empty();
            } else {
                return Optional.empty();
            }

        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private boolean hasPSHeader(byte[] data) {
        String possibleHeader = new String(data, StandardCharsets.US_ASCII).toLowerCase();
        return possibleHeader.startsWith(header);
    }
}
```