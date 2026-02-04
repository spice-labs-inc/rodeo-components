package io.spicelabs.rodeocomponents.APIS.artifacts;

import java.util.List;

public interface RodeoProcessItems {
    void onCompletion(RodeoArtifact artifact);
    int length();
    Pair<List<Pair<RodeoArtifact, RodeoItemMarker>>, ArtifactHandler> getItemsToProcess();
}
