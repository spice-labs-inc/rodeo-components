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

import java.io.FileInputStream;
import java.io.InputStream;

public interface ContainerFactory {
    String getName();
    HandlerResult canHandle(String mimeType);
    ContainerHandler buildHandler(String mimeType, InputStream stm);
    ContainerHandler buildHandler(String mimeType, FileInputStream stm);
    void onContainerProcessed(ContainerHandler handler);
}
