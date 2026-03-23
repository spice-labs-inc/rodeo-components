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

import java.io.File;

/**
 * Defines how container items should be reported to goat rodeo. A producer of items should expect to
 * call this multiple times for each item. Each time receiveItem is called, it will return a ContainerReceiver
 * that is likely different from the one that is called. Therefore it is important to never assume that the
 * returned receiver is the same.
 */
public interface ContainerReceiver {
    /**
     * Provide an item to a receiver which will process it and accumulate it.
     * @param handler The handler that is providing the item
     * @param item The item provided
     * @param tempDirectory a temporary directory (this is provided by ContainerFactory.buildHandler)
     * @return a (likely) new ContainerReceiver that has received the item
     */
    public ContainerReceiver receiveItem(ContainerHandler handler, ContainerItem item, File tempDirectory);
}
