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
 * A type to represent metadata associated with an artifact that is being processed
 */
public class Metadata {
    private final MetadataTag _tag;
    private final String _value;
    /**
     * Constructs a new Metadata object with th given tag and value
     * @param tag the tag to associate with the metadata
     * @param value the value of the metadata
     */
    public Metadata(MetadataTag tag, String value) {
        _tag = tag;
        _value = value;
    }
    /**
     * Gets the tag associated with this metadata objet
     * @return the tag associated with the metadata
     */
    public MetadataTag tag() {
        return _tag;
    }
    
    /**
     * Gets the value associated with this metadata object
     * @return the value associated with the metadata
     */
    public String value() {
        return _value;
    }
}
