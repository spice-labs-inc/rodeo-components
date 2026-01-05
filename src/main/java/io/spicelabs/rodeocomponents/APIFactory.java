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


/**
 * An APIFactory is an object which builds API objects to be used by components. A component which wants to use an API
 * will call an {@link io.spicelabs.rodeocomponents.APIFactorySource} in order to import the factory which owns that API.
 * @param <T> a type which implements an API
 */
public interface APIFactory<T extends API> {
    /**
     * Returns the name of the API. The name will be the same as the name used to import the factory.
     * @return A string representing the name of the API.
     */
    String name();
    /**
     * Builds an API for a given {@link io.spicelabs.rodeocomponents.RodeoComponent}.abstract
     * @param subscriber the component which is building the API
     * @return The API that was requested.
     */
    T buildAPI(RodeoComponent subscriber);
}
