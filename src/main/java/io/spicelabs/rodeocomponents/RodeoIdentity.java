package io.spicelabs.rodeocomponents;

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

/**
 * Defines an interface for reporting information about a component.
 */
public interface RodeoIdentity {
  /**
   * Returns the name of the component. While it is possible to provide a name which is "unusual" by containing whitespace
   * or emoji or other unusual characters, this should be avoided as it will make some routine tasks for the component
   * onerous at best. Components with conflicting names will be discarded.
   * @return the name of the component.
   */
  String name();
  /**
   * Returns the name of the publisher of the component. This will be used for clarifying the origin of the component.
   */
  String publisher();
}
