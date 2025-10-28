package io.spicelabs.rodeocomponents;

public interface APIFactory<T extends API> {
    String name();
    T buildAPI();
}
