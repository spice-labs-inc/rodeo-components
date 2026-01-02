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
 * An API is a unit of code which performs a specific function or set of functions. In goat rodeo, an API
 * is built by a factory. The factory comes from a {@link io.spicelabs.rodeocomponents.APIFactorySource}. A
 * component will import the factory from the factory source and then will call the factory to get the API.
 * This level of indirection is necessary so that an API can, if needed, be tailored to the component that needs
 * it. As an example, the logging API can provide a logging service that automatically adds information in the log
 * which includes information about the component that is logging.
 * <p>
 * All services provided by goat rodeo <b>must</b> implement the API interface.
 */
public interface API extends Releasable {
}
