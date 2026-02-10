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

import java.util.List;

/**
 * An interface that describes what items need to be processed.
 */
public interface RodeoProcessItems {
    /**
     * This method gets called when the artifact has been completely processed
     * @param artifact the artifact that was processed
     */
    void onCompletion(RodeoArtifact artifact);
    /**
     * The number of items that will be processed
     * @return the number of items that will be processed
     */
    int length();
    /**
     * Gets the items to process. This consists of a list of items and an artifact handler that
     * will be called for each item that gets processed. The list consists of items. Each item
     * is a pair of an artifact and a marker to associate with it. It is up to the component to
     * decide what is in the contents of the {@link RodeoItemMarker}. For example, if the component
     * processed both source and object files, it might contain a tag of some kind to note that one
     * artifact is a source file and a different tag to indicate that it's an object file.
     * @return
     */
    Pair<List<Pair<RodeoArtifact, RodeoItemMarker>>, ArtifactHandler> getItemsToProcess();
}
