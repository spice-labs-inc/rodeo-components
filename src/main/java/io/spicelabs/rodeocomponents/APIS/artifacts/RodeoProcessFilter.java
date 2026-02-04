package io.spicelabs.rodeocomponents.APIS.artifacts;

import java.util.List;
import java.util.Map;

public interface RodeoProcessFilter {
  String getName();
  List<Pair<RodeoArtifact, RodeoProcessItems>> filterByName(Map<String, List<RodeoArtifact>> namesToFilter);
  List<Pair<RodeoArtifact, RodeoProcessItems>> filterByUUID(Map<String, List<RodeoArtifact>> uuidsToFilter);
}
