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
 * An implementation of ContainerHandler that does nothing. Instead of returning null from
 * the ContainerFactory methods that build factories that are unused, you can return an
 * EmptyContainerHandler which will do nothing. Its getItems() method returns Stream.empty()
 * and its onItemProcessed method does nothing.
 */
public class EmptyContainerHandler implements ContainerHandler {
    /**
     * Gets an singleton instance of the EmptyContainerHandler
     */
    public static final ContainerHandler empty = new EmptyContainerHandler();

    private EmptyContainerHandler() { }

    @Override
    public ContainerReceiver produceItems(ContainerReceiver receiver) {
        return receiver;
    }

    @Override
    public void onItemProcessed(ContainerItem item) {
    }
  
}
