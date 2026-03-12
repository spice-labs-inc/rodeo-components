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

import java.util.stream.Stream;

/**
 * Represents a class that will be called to handle the contents of a container.
 */
public interface ContainerHandler {
    /**
     * Get the items within the container. <B>Note</B>: when creating the stream, do not
     * use a parallel stream as there will be contention over the initial InputStream from
     * which the items are being extracted.
     * @return a Stream of items.
     */
    Stream<ContainerItem> getItems();
    /**
     * Called when an item has been processed. This gives a ContainerHandler the opportunity
     * to free fresources, if needed.
     * @param item the item that has been processed.
     */
    void onItemProcessed(ContainerItem item);
}
