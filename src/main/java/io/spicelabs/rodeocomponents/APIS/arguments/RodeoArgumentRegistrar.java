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

import io.spicelabs.rodeocomponents.API;

/**
 * The RodeoArgumentRegistrar is the main API used for consuming arguments passed into goat rodeo.
 * <p>
 * Arguments will be seen in the form <code>--component &lt;component-name&gt; parameter+</code>. For example,
 * if your component is named <code>splunge</code> an argument of the form <code>--component splunge cream-cheese</code>
 * will available to <code>splunge</code> via its {@link io.spicelabs.rodeocomponents.APIS.arguments.RodeoArgumentListener}.
 * <p>
 * In order to receive arguments, a component needs create a <code>RodeoArgumentRegistrar</code> API via a factory imported
 * from an instance of {@link io.spicelabs.rodeocomponents.APIFactorySource}.
 */
public interface RodeoArgumentRegistrar extends API {
    /**
     * Register a listener for processing command line arguments.
     * @param listener an argument listener
     */
    void register(RodeoArgumentListener listener);
}
