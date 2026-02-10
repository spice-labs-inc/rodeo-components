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
import java.util.Map;

/**
 * Represents a filter for artifacts to be processed.
 */
public interface RodeoProcessFilter {
  /**
   * Defines the name of the filter.
   * @return the name of the filter
   */
  String getName();
  /**
   * Given a set of {@link RodeoArtifact} objects, return a list of triples that describe what will be processed by
   * this handler. For each artifact, this will return a {@link Triple} containing the items that describe what it will
   * process. This triple consists of:
   * <UL>
   * <LI>the name of the artifact from namesToFilter</LI>
   * <LI>the RodeoArtifact from the namesToFilter</LI>
   * <LI>a RodeoProcessItems which describes artifacts and how they will be processed</LI>
   * </UL>
   * @param namesToFilter
   * @return a {@link Triple} containing the items that describe what will be processed.
   */
  List<Triple<String, RodeoArtifact, RodeoProcessItems>> filterByName(Map<String, List<RodeoArtifact>> namesToFilter);
}
