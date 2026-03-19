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

package io.spicelabs.rodeocomponents.APIS.containers;

/**
 * This is the main API that is used to handle containers. The starting point is a {@link ContainerFactory} which
 * will examine the MIME type of a stream and (possibly) expand it into new artifacts.
 */
public class ContainerConstants {
    /**
     * Defines the name of the ContainerFactoryRegistrar
     */
    public static final String NAME = "ContainerFactoryRegistrar";
}
