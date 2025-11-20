package io.spicelabs.rodeocomponents.APIS;

import io.spicelabs.rodeocomponents.API;
public interface RodeoLogger extends API {
  void error(String message);
  void error(String message, Throwable cause);
  void warn(String message);
  void info(String message);
  void debug(String message);
}
