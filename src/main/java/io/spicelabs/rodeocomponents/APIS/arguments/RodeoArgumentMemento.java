package io.spicelabs.rodeocomponents.APIS.arguments;

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
 * The RodeoArgumentMemento interface is used by components to hold intermediate information for processing arguments.
 * A component may create an object that implements this interface which holds data which is used for configuration of the
 * component. It makes no difference to goat rodeo how the component uses its memento. Goat rodeo will, however, honor changes in the
 * actual memento instance rather than assuming that the same one is used across all calls. This allows the component to adopt an
 * approach where the memento is a single mutable object that changes in its contents or it can adopt an approach where the memento
 * is an immutable object that is replaced with any change to it.
 */
public interface RodeoArgumentMemento { }
