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

import java.io.InputStream;
import java.util.function.Function;

/**
 * Represents an artifact that is being processed.
 */
public interface RodeoArtifact {
    /**
     * Returns the nominal path of the artifact. <B>Do not depend on this being a file system path</B>
     * @return the nominal path of the artifact
     */
    String getPath();
    /**
     * Returns the size of the artifact in bytes.
     * @return the size of the artifact in bytes
     */
    long getSize();
    /**
     * Returns the MIME type of the artifact
     * @return the MIME type of the artifact
     */
    String getMimeType();
    /**
     * Indicates whether or not the artifact is represented by a real file
     * @return true if the artifact is a real file, false otherwise
     */
    boolean getIsRealFile();
    /**
     * Returns a unique identifier associated with the artifact
     * @return a unique identifier associated with the artifact
     */
    String getUuid();
    /**
     * Returns the file name of the artifact
     * @return the file name of the artifact
     */
    String getFilenameWithNoPath();

    /**
     * Executes a function with an InputStream containing the artifact
     * @param <T> The return type of the function
     * @param func The function to execute
     * @return The return value of the function
     */
    <T> T withInputStream(Function<InputStream, T> func);
}
