package io.spicelabs.rodeocomponents;

public interface APIFactoryReceiver {
  public <T extends API> void publishFactory(Object publisher, String apiName, APIFactory<T> apiFactory);
}
