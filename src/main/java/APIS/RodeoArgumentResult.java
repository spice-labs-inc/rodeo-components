package io.spicelabs.rodeocomponents.APIS;

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
