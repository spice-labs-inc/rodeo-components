package io.spicelabs.rodeocomponents;

public interface APIFactorySource {
    public <T extends API> APIFactory<T> getAPIFactory(String name);
}
