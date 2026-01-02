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

import java.util.Objects;
import java.util.Optional;

public class RodeoArgumentResult {
    private final Optional<RodeoArgumentMemento> _memento;
    private final Optional<String> _error;

    private RodeoArgumentResult(Optional<RodeoArgumentMemento> memento, Optional<String> error) {
        _memento = memento;
        _error = error;
    }
    public Optional<RodeoArgumentMemento> getMemento() {
        return _memento;
    }

    public Optional<String> getError() {
        return _error;
    }
  
    public static RodeoArgumentResult fromMemento(RodeoArgumentMemento memento) {
        Objects.requireNonNull(memento, "memento");
        return new RodeoArgumentResult(Optional.of(memento), Optional.empty());
    }

    public static RodeoArgumentResult fromError(String error) {
        Objects.requireNonNull(error, "error");
        return new RodeoArgumentResult(Optional.empty(), Optional.of(error));
    }
}
