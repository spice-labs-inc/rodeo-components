package io.spicelabs.rodeocomponents.APIS.purls;

import java.net.MalformedURLException;

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
 * A class used for making PackageURLs. This class is a factory with a fluent interface.
 * Each method, except {@link #toPurl() toPurl} will return a (possibly) new instance of a
 * PurlFactory.
 * <p>The <a href="https://github.com/package-url/purl-spec?tab=readme-ov-file#some-purl-examples">specification for PackageURLs</a> spells
 * out the required elements to construct a correct PackageURL.
 */
public interface PurlFactory {
    /**
     * Adds the <code>type</code> element to the factory
     * @param type The type to add
     * @return a modified PurlFactory
     */
    PurlFactory withType(String type);

    /**
     * Adds the <code>namespace</code> element to the factory
     * @param namespace the name space to add
     * @return a modified PurlFactory
     */
    PurlFactory withNamespace(String namespace);

    /**
     * Adds the <code>name</code> element to the factory
     * @param name the name to add
     * @return a modified PurlFactory
     */
    PurlFactory withName(String name);

    /**
     * Adds the version element to the factory
     * @param version the version to add
     * @return a modified PurlFactory
     */
    PurlFactory withVersion(String version);

    /**
     * Adds the subpath element to the factory
     * @param subpath the subpath to add
     * @return a modified PurlFactory
     */
    PurlFactory withSubpath(String subpath);

    /**
     * Adds the key and value qualifier to the factory.
     * @param key the key of the qualifier
     * @param value the value to associatate with the key in the qualifier
     * @return a modified PurlFactory
     */
    PurlFactory withQualifier(String key, String value);
    
    /**
     * Generates a Purl from the factory.
     * @return a new {@link Purl} representing the PackageURL
     * @throws MalformedURLException if the factory is incomplete or contains invalid elements.
     */
    Purl toPurl() throws MalformedURLException;
}
