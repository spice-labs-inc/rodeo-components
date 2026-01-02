package io.spicelabs.rodeocomponents.APIS;

import io.spicelabs.rodeocomponents.API;

public interface RodeoArgumentRegistrar extends API {
    void register(RodeoArgumentListener listener);
}
