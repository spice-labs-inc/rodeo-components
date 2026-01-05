package io.spicelabs.rodeocomponents;

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

import java.util.Optional;

/**
 * APIFactorySource is an interface that defines how components will import factories from goat rodeo.
 */
public interface APIFactorySource {
    /**
     * Attempt to import an APIFactory.
     * @param <T> The type of the requested API
     * @param name The name of the API. This name should be available as a constant from the API author.
     * @param subscriber The component which is importing the factory
     * @param apiType The type of the API which is being requested.
     * @return An optional type of the requested APIFactory
     * <p>Example - Importing an API factory
     * {@code Optional<APIFactory<RodeoLogger>> loggerFactoryOpt = factorySource.getAPIFactory<RodeoLogger>(RodeoLoggerConstants.NAME, this, RodeoLogger.class)}
     */
    public <T extends API> Optional<APIFactory<T>> getAPIFactory(String name, RodeoComponent subscriber, Class<T> apiType);
}
