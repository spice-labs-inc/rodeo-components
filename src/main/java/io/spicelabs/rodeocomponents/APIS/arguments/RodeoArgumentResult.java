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

/**
 * A class to hold a result from processing an argument. A result is either a memento to be used in future processing
 * or an error to indicate that the component couldn't process the argument. Instances of <code>RodeoArgumentResult</code>
 * are constructed through the factory methods <code>fromMemento</code> or <code>fromError</code>.
 */
public class RodeoArgumentResult {
    private final Optional<RodeoArgumentMemento> _memento;
    private final Optional<String> _error;

    private RodeoArgumentResult(Optional<RodeoArgumentMemento> memento, Optional<String> error) {
        _memento = memento;
        _error = error;
    }

    /**
     * Gets the memento associated with this result. If {@link #hasError()} returns false, this optional value will contain a
     * valid memento.
     * @return an optional memento
     */
    public Optional<RodeoArgumentMemento> getMemento() {
        return _memento;
    }

    /**
     * Gets the error associated with this result. If {@link #hasError()} returns true, this optional value will contain a valid
     * error string.
     * @return the error associated with this result
     */
    public Optional<String> getError() {
        return _error;
    }

    /**
     * Gets an indication as to whether or not the <code>RodeoArgumentResult</code> represents an error.
     * @return <code>true</code> if the <code>RodeoArgumentResult</code> contains an error <code>false</code> otherwise.
     */
    public boolean hasError() {
        return _error.isPresent();
    }
  
    /**
     * Constructs a new <code>RodeoArgumentResult</code> which will contain the provided memento.
     * @param memento a non-null memento to be used in future processing
     * @return a new instance of <code>RodeoArgumentResult</code>
     */
    public static RodeoArgumentResult fromMemento(RodeoArgumentMemento memento) {
        Objects.requireNonNull(memento, "memento");
        return new RodeoArgumentResult(Optional.of(memento), Optional.empty());
    }

    /**
     * Construct a new <code>RodeoArgumentResult</code> which will contain the provided error.
     * @param error an error message to be reported.
     * @return a new instance of <code>RodeoArgumentResult</code>
     */
    public static RodeoArgumentResult fromError(String error) {
        Objects.requireNonNull(error, "error");
        return new RodeoArgumentResult(Optional.empty(), Optional.of(error));
    }
}
