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
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface ArtifactHandler {
  boolean requiresFile();
  ArtifactMemento begin(InputStream stream, RodeoArtifact artifact, WorkItem item);
  ArtifactMemento begin(FileInputStream stream, RodeoArtifact artifact, WorkItem item);
  List<Purl> getPurls(ArtifactMemento memento, RodeoArtifact artifact, WorkItem item);
  List<Metadata> getMetadata(ArtifactMemento memento, RodeoArtifact artifact, WorkItem item);
  void augment(ArtifactMemento memento, RodeoArtifact artifact, WorkItem item, ParentFrame parent, BackendStorage storage);
  void postChildProcessing(ArtifactMemento memento, Optional<List<String>> gitoids, BackendStorage storage);
  void end(ArtifactMemento memento);
}
