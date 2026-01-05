package io.spicelabs.rodeocomponents.APIS.arguments;

/* Copyright 2026 Stephen Hawley, Spice Labs, Inc. & Contributors

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

import java.util.List;

/**
 * RodeoArgumentListener defines the interface used for processing command line arguments for goat rodeo components.
 * <p>
 * The general process is that the component will register a listener for command line arguments which will come in
 * in the form {@code --component &lt;component-name&gt; parameter+}. Goat rodeo will accumulate all arguments that are
 * destined for components and after all other arguments have been processed, goat rodeo will call registered {@code RodeoArgumentListener}
 * objects to inform them of each argument that has been directed to it along with the parameters. The process works as follows:
 * <p>
 * <ul>
 * <li>Goat rodeo will call {@code begin} and the component will return a {@link io.spicelabs.rodeocomponents.APIS.arguments.RodeoArgumentMemento}.</li>
 * <li>Goat rodeo will call {@code receiveArgument} for each instance of {@code --component name} and the component will return a {@link io.spicelabs.rodeocomponents.APIS.arguments.RodeoArgumentResult}.</li>
 * <li>Goat rodeo will call {@code end} after all the arguments have been handled.
 * </ul>
 * <p>
 * Goat rodeo doesn't presume that there is any meaning to the arguments and parameters passed to a {@code RodeoArgumentListener}. It
 * is up to the listener to understand the contents. For example, if a component wanted to adopt the approach where it has the format
 * {@code --component watcher verbose --component watcher verbose} to indicate more verbosity or {@code --component watcher verbosity 5},
 * it's up to the component to make that choice.
 * <p>
 * Processing is based on the memento pattern where the component passes back its own object after each step of processing. Goat rodeo
 * doesn't do anything with the memento other than passing it back in a subsequent call. A component might want to store intermediate
 * information in the memento and then when {@code end} is called, it can finish configuration and report errors, if any.
 * <p>
 * A {@code RodeoArgumentResult} contains either an error or a memento. The memento can be the same one as previously or a new one. That
 * choice is in the hands of the component writer.
 */
public interface RodeoArgumentListener {
  /**
   * Called by goat rodeo, {@code begin} is used to indicate that argument processing for the component is going to begin.
   * @return a memento that will be passed back to the component.
   */
  RodeoArgumentMemento begin();
  /**
   * Called by goat rodeo, {@code receiveArgument} will be called for each argument destined for a component. The component
   * will accept the parameters and return a result that contains either a memento or an error in processing the arugment.
   * @param parameters a list of parameters passed in with the argument
   * @param memento the last memento that was returned from either a call to {@code begin} or the last call to {@code receiveArgument}
   * @return a {@link io.spicelabs.rodeocomponents.APIS.arguments.RodeoArgumentResult} indicating that the arugment was received or an error message
   * failure.
   */
  RodeoArgumentResult receiveArgument(List<String> parameters, RodeoArgumentMemento memento);
  /**
   * Called by goat rodeo, {@code end} will be called after the last argument for this component has been handled.
   * @param memento the last memento returned by the component
   * @return a {@link io.spicelabs.rodeocomponents.APIS.arguments.RodeoArgumentResult} indicating that the arugment was received or an error message
   * failure.
   */
  RodeoArgumentResult end(RodeoArgumentMemento memento);
  /**
   * Called by goat rodeo, returns a description of command line arguments accepted by the component.
   * @return a String describing the command line arguments accepted by the component
   */
  String getDescription();
}
