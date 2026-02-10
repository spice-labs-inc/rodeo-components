package io.spicelabs.rodeocomponents.APIS.artifacts;

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
 * MetadataTag represents a standard set of possible metadata items to associate
 * with an artifact
 */
public enum MetadataTag {
    /**
     * The full name of the artifact such as "Foo Systems Turboencabulator v0.1.5"
     */
    NAME,
    /**
     * A simplified or abbreviated name of the artifact such as "Turboencabulator"
     */
    SIMPLE_NAME,
    /**
     * A version of this artifact
     */
    VERSION,
    /**
     * The locale of the artifact, two lowercase letters representing the country code and if possible
     * followed by a dash and a two letter lowercase language code
     */
    LOCALE,
    /**
     * The public key associated with the artifact
     */
    PUBLIC_KEY,
    /**
     * The publisher of the artifact
     */
    PUBLISHER,
    /**
     * The date of publication of the artifact
     */
    PUBLICATION_DATE,
    /**
     * A copyright notice for the artifact
     */
    COPYRIGHT,
    /**
     * A description of the artifact
     */
    DESCRIPTION,
    /**
     * A trademark statement for the artifact
     */
    TRADEMARK,
    /**
     * An identifier associated with the artifact
     */
    ARTIFACT_ID,
    /**
     * A license or license statement for the artifact
     */
    LICENSE,
    /**
     * Dependencies for the artifact which will be formatted JSON that describes all the
     * dependencies. It is in the form:
     * {@code {dependencies: [{"name":"theName","version":"0.0.0","public_key_token":"hexstring"}]}}
     */
    DEPENDENCIES,
}
