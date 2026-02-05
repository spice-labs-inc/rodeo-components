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

import io.spicelabs.rodeocomponents.APIS.purls.Purl;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public interface WorkItem {
    String getIdentifier();
    Set<StringPair> getConnections();
    WorkItem withNewConnection(String edgeType, String id);
    WorkItem merge(WorkItem other);
    byte[] getMd5();
    boolean compareMd5(WorkItem other);
    boolean isRootWorkItem();
    List<StringPair> referencedFromAliasOrBuildOrContained();
    WorkItem createOrUpdateInStorage(BackendStorage store, Function<WorkItem, String> context);
    WorkItem updateTheBackReferences(BackendStorage store, ParentFrame frame);
    List<String> containedGitoids();
    WorkItem enhanceWithPurls(List<Purl> purls);
    WorkItem enhanceWithMetadata(Optional<String> parent, Map<String, Set<StringPairOptional>> extra, List<String> filenames, List<String> mimeTypes);
}
