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

/**
 * An APIFactoryReceiver is responsible for maintaining a list of API factories. If a component publishes an API, it will
 * call the {@link #publishFactory} method in the receiver to do so.
 */
public interface APIFactoryReceiver {
  /**
   * Publish an {@link io.spicelabs.rodeocomponents.APIFactory} for use by other components or by goat rodeo.
   * @param <T> The type of the API which is being published
   * @param publisher The component which is publishing the factory
   * @param apiName The name of the API. This name should be made available as a public constant.
   * @param apiFactory an {@link io.spicelabs.rodeocomponents.APIFactory} which will be used for building API objects
   * @param apiType the type of the API that will be returned by the factory
   */
  public <T extends API> void  publishFactory(RodeoComponent publisher, String apiName, APIFactory<T> apiFactory, Class<T> apiType);
}
