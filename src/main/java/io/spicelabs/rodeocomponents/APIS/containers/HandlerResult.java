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
 * A type that is used to describe how a ContainerFactory will be used to handle
 * a candidate container.
 */
public enum HandlerResult {
    /**
     * Indicates that the ContainerFactory will not handle the candidate container
     */
    NO,
    /**
     * Indicates that the ContainerFactory will handle the candidate container and can
     * work with a plain InputStream.
     */
    WITH_INPUT_STREAM,
    /**
     * Indicated that the ContainerFactory will handle the candidate container and can
     * work with a FileInputStream.
     */
    WITH_FILE_INPUTSTREAM
}
