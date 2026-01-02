package io.spicelabs.rodeocomponents.APIS;

import java.util.List;

public interface RodeoArgumentListener {
  RodeoArgumentMemento begin();
  RodeoArgumentResult receiveArgument(List<String> parameters, RodeoArgumentMemento memento);
  RodeoArgumentResult end(RodeoArgumentMemento memento);
}
