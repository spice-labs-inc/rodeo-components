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
import java.io.FileInputStream;
import java.nio.file.Path;

/**
 * Defines how a type that makes ContainerHandler objects will behave. This is used
 * for extracting elements from a container such as a tar file.
 */
public interface ContainerFactory {
    /**
     * Gets the name of the handler
     * @return the name of the handler
     */
    String getName();
    /**
     * Indicates if and how the factory will respond to a container with the give mime type
     * @param mimeType the mime type of the container
     * @return return HandlerResult.NO if the factory can't handle the mime type, HandlerResult.WITH_INPUT_STREAM if
     * the handler requires an InputStream as input, otherwise HandlerResult.WITH_FILE_INPUT_STREAM if the handler
     * requires a FileInputStream. <B>Note</B>: working with InputStream objects will be more efficient.
     */
    HandlerResult canHandle(String mimeType);
    /**
     * Build a handler for the given mimeType using an InputStream
     * @param mimeType the mime type of the container
     * @param stm the stream for the container
     * @param tempDirectory a path to a directory for creating temporary files
     * @return a new ContainerHandler for the container
     */
    ContainerHandler buildHandler(String mimeType, StreamProvider provider, Path tempDirectory);
    /**
     * Build a handler for the given mimeType using a FileInputStream
     * @param mimeType the mime type of the container
     * @param stm the stream for the container
     * @param tempDirectory a path to a directory for creating temporary files
     * @return a new ContainerHandler for the container
     */
    ContainerHandler buildHandler(String mimeType, FileInputStream stm, Path tempDirectory);
    /**
     * Build a handler for the given mimeType using a File
     * @param mimeType the mime type of the container
     * @param file the file for the container
     * @param tempDirectory a path to a directory for creating temporary files
     * @return a new ContainerHandler for the container
     */
    ContainerHandler buildHandler(String mimeType, File file, Path tempDirectory);
    /**
     * Called when the container has been fully processed. This is an opportunity for the handler to clean up
     * any resources.
     * @param handler the handler that processed the container.
     */
    void onContainerProcessed(ContainerHandler handler);
}
