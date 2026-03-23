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
 * Represents a class that will be called to handle the contents of a container.
 */
public interface ContainerHandler {
    /**
     * Called when a container should produce its items. The items will be provided to the
     * given receiver.
     * @param receiver a receiver for the items
     * @return the final receiver after producing items
     */
    ContainerReceiver produceItems(ContainerReceiver receiver);
    /**
     * Called when an item has been processed. This gives a ContainerHandler the opportunity
     * to free fresources, if needed.
     * @param item the item that has been processed.
     */
    void onItemProcessed(ContainerItem item);
}
