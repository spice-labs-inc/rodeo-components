package io.spicelabs.rodeocomponents;

import java.util.Optional;

public interface APIFactorySource {
    public <T extends API> Optional<APIFactory<T>> getAPIFactory(String name, RodeoComponent subscriber);
}
