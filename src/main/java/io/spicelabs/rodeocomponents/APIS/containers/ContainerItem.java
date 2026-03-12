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

import java.io.InputStream;

/**
 * An item that has been extracted from a container.
 * @param stm A stream that contains the data representing the item
 * @param forceTempFile if true, when the item is converted into an artifact, it will be forced into a file representation
 * @param itemPath a string representing the path the item within the container. Note: this should not be an absolute path
 * @param itemSize the size of the item in bytes within the stream
 */
public record ContainerItem(InputStream stm, boolean forceTempFile, String itemPath, long itemSize) {

}
