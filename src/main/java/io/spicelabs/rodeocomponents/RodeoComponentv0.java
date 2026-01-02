package io.spicelabs.rodeocomponents;

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

import java.lang.Runtime.Version;

/**
 * The zeroth version of the rodeo component system. All components are required to implement this interface.
 * <p>
 * Components will be loaded in a process whereing the component host will discover components and determine if they
 * can be supported. This process following a number of steps:
 * <ul>
 * <li>initialization - the component is given an opportunity to do component-specific initialization. If a component can't
 * proceed, it should throw an Exception of some kind.</li>
 * <li>identification - the component will return an object providing information about the component</li>
 * <li>versioning - the component will return information about the version of component system that the component is compiled against.</li>
 * <li>exporting - if the component has APIs that it which to export, it will do so in this step.</li>
 * <li>importing - the component will use this step to import APIs that have been exported by goat rodeo or other components</li>
 * <li>completion - the component is given the opportunity to perform any final work. This may be registering for notifications, starting
 * work, or any other component specific tasks</li>
 * <li>shutting down - the component will be informed that it's time to shut down and at this point it needs to do any clean up</li>
 * </ul>
 */
public interface RodeoComponentv0 {
  /**
   * Initialize the component. If the component cannot initialize, it should throw an Exception.
   * @throws an Exception if the component cannot initialize.
   */
  void initialize() throws Exception;
  /**
   * Gets the identity of the component.
   * @return The identity of the component
   */
  RodeoIdentity getIdentity();
  /**
   * Gets the version that the component was compiled against. This can be access with {@link io.spicelabs.rodeocomponents.RodeoEnvironment#currentVersion}
   * @return the component system version
   */
  Version getComponentVersion();
  /**
   * Gives a component the opportunity to export any API factories via the supplied {@link io.spicelabs.rodeocomponents.APIFactoryReceiver}.
   * <p>
   * While it is possible for a component to export API factories at any time using the supplied receiver, this is not useful in that
   * there is no guarantee that any other component will see the factories during the importing phase.
   * @param receiver the object which receives exported API factories.
   */
  void exportAPIFactories(APIFactoryReceiver receiver);
  /**
   * Gives a component the opportunity to import any API factories via the supplied {@link io.spicelabs.rodeocomponents.APIFactorySource}.
   * @param factorySource the source from which APIFactory objects are obtained
   */
  void importAPIFactories(APIFactorySource factorySource);
  /**
   * Gives a component an opportunity to perform any work that is necessary before goat rodeo starts.
   */
  void onLoadingComplete();
  /**
   * Gives a component an opportunity to clean up and release resources when goat rodeo is finishing.
   */
  void shutDown();
}

