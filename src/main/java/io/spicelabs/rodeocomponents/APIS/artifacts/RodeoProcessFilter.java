package io.spicelabs.rodeocomponents.APIS.artifacts;

import java.util.List;
import java.util.Map;

public interface RodeoProcessFilter {
  String getName();
  List<Triple<String, RodeoArtifact, RodeoProcessItems>> filterByName(Map<String, List<RodeoArtifact>> namesToFilter);
}
