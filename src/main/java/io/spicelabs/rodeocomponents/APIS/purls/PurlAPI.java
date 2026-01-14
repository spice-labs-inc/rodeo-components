package io.spicelabs.rodeocomponents.APIS.purls;

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
 * This defines the API for making a PackageURL. Calling <code>newPurlFactory</code> will create
 * a class that can be used for aggregating elements of a PackageURL. See {@link PurlFactory} for details.
 */
public interface PurlAPI extends API {
    /**
     * Creates a new new {@link PurlFactory} for building a PackageURL.
     * @return  new PurlFactory
     * @see PurlFactory 
     */
    PurlFactory newPurlFactory();
}
