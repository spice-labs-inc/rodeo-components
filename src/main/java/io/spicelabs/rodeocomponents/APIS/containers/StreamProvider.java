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
import java.util.function.Function;

/**
 * Provides InputStream objects to a consumer.
 */
public interface StreamProvider {
    /**
     * operateOnStream gives a function an opportunity to perform operations on a stream. This method
     * can be called multiple times and the operator is guaranteed to receive the same data each time (provided
     * it hasn't been modified by outside means), but it is not guaranteed to receive the same stream object.
     * This method is useful for containers that consume a stream and may need to read from the start of the
     * stream multiple times. <B>Note</B>: The InputStream passed to the operator will be invalid after the
     * operator has exited.
     * @param <T> client defined type to serve as a result from reading from the stream
     * @param operator a function to operate on the stream
     * @return a client defined return value
     */
    <T> T operateOnStream(Function<InputStream, T> operator);
}
