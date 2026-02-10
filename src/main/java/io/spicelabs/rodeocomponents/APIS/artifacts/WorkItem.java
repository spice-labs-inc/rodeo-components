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

/**
 * A class which represents an item that aggregates data while an artifact is being processed.
 */
public interface WorkItem {
    /**
     * Get the identifier associated with the work item.
     * @return a String representing the identifier
     */
    String getIdentifier();
    /**
     * Get a set of connections between the work item and other items
     * @return
     */
    Set<StringPair> getConnections();
    /**
     * Get a new WorkItem that has been updated with a new connection.
     * @param edgeType type of connection. See {@link EdgeTypeConstants} for supported connection types.
     * @param id the id of the work item that is being connected to
     * @return a new WorkItem that has the connection added to it
     */
    WorkItem withNewConnection(String edgeType, String id);
    /**
     * Get a new WorkItem that represents a merge with the supplied other WorkItem
     * @param other the WorkItem to merge in
     * @return a new WorkItem that has been updated
     */
    WorkItem merge(WorkItem other);
    /**
     * Get the MD5 hash for this WorkItem
     * @return a byte array with the MD5 hash
     */
    byte[] getMd5();
    /**
     * Compare the MD5 hash with the other WorkItem
     * @param other the WorkItem that will be compared to
     * @return true if the hashes are the same, false otherwise
     */
    boolean compareMd5(WorkItem other);
    /**
     * Check to see if this WorkItem is a root of a tree of WorkItem objects
     * @return true if this WorkItem is the root, false otherwise
     */
    boolean isRootWorkItem();
    /**
     * Gets a list of WorkItems that are referenced from this one where the connection type is in the set of
     * alias from, build from, or contained by.
     * @return a list of pairs representing the connections. The first item is the connection type, the second the ID of the
     * item that is referenced by the connection
     */
    List<StringPair> referencedFromAliasOrBuildOrContained();
    /**
     * Update the WorkItem in the backend storage with this WorkItem. 
     * @param store the backend storage to write to
     * @param context a function that will be called with the item
     * @return the supplied WorkItem if it wasn't in the backend storage or a new item that has been merged with the existing one
     */
    WorkItem createOrUpdateInStorage(BackendStorage store, Function<WorkItem, String> context);
    /**
     * Updates any back references in this WorkItem in the backend storage.
     * @param store the backend storage to write to
     * @param frame parent context of the WorkItem
     * @return an updated WorkItem
     */
    WorkItem updateTheBackReferences(BackendStorage store, ParentFrame frame);
    /**
     * Gets a set of GitOIDs that are contained within this WorkItem
     * @return a list of Strings, each of which is a GitOID
     */
    List<String> containedGitoids();
    /**
     * Merges a list of package URLs into this WorkItem, returning a new item.
     * @param purls a list of package URLs to merge in
     * @return a new WorkItem with the specified package URLs added if the list is not empty, this otherwise
     */
    WorkItem enhanceWithPurls(List<Purl> purls);
    /**
     * Merges metadata into the WorkItem returning a new WorkItem
     * @param parent an optional ID of a parent WorkItem
     * @param extra a collection of optional metadata to add
     * @param filenames a list of file names associated with this WorkItem
     * @param mimeTypes a list of MIME types to associated with this WorkItem
     * @return a new WorkItem with the updated information
     */
    WorkItem enhanceWithMetadata(Optional<String> parent, Map<String, Set<StringPairOptional>> extra, List<String> filenames, List<String> mimeTypes);
}
