package io.spicelabs.rodeocomponents;

public interface APIFactoryReceiver {
  public <T extends API> void publishFactory(RodeoComponent publisher, String apiName, APIFactory<T> apiFactory);
}
