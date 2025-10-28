package io.spicelabs.rodeocomponents;
import java.lang.Runtime.Version;

public interface RodeoComponentv0 {
  void initialize();
  RodeoIdentity getIdentity();
  Version getComponentVersion();
  void exportAPIFactories(APIFactoryReceiver receiver);
  void importAPIFactories(APIFactorySource factorySource);
  void shutDown();
}

