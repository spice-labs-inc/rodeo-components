package io.spicelabs.rodeocomponents.APIS.mimes;

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
import java.util.Optional;

public interface MimeIdentifer<T extends InputStream> {
    int preferredHeaderLength();
    boolean canHandleHeader(byte[] header);
    Optional<String> identifyMimeType(T stream, String mimeSoFar);
    Optional<String> finalMimeAdjustment(T stream, String mimeSoFar);
}
