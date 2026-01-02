package io.spicelabs.rodeocomponents.APIS.logging;

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

import io.spicelabs.rodeocomponents.API;

/**
 * The RodeoLogger interface defines the actions available to a component that
 * needs to create a log of actions or information during a run of goat-rodeo.
 * <p>
 * The factory to create this API is retrieved from an imported instance of
 * {@link io.spicelabs.rodeocomponents.APIFactorySource}. 
 */
public interface RodeoLogger extends API {
  /**
   * Logs the given message as an error.
   * @param message a message to display in the log
   */
  void error(String message);
  /**
   * Logs the given message as an error and includes a Throwable object that was
   * presunably the cause of the error.
   * @param message a message to display in the log
   * @param cause the cause of the error
   */
  void error(String message, Throwable cause);
  /**
   * Logs the given message as a warning.
   * @param message a message to display in the log
   */
  void warn(String message);
  /**
   * Logs the given message as information.
   * @param message a message to display in the log
   */
  void info(String message);
  /**
   * Logs the given message as debug information.
   * @param message a message to display in the log
   */
  void debug(String message);
}
