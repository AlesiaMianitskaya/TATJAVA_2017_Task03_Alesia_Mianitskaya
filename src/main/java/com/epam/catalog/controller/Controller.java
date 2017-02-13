package com.epam.catalog.controller;

import com.epam.catalog.command.Command;

/**
 * Execute command from request
 */
public class Controller {
  private final CommandProvider commandProvider = new CommandProvider();
  private final String separator = " ";

  /**
   * execute command
   *
   * @param request line contains written command
   * @return response to the request
   */
  public String executeGoal(String request){
    String commandName;
    Command executionCommand;

    commandName = request.split(separator)[0];// уString есть  перегруженный split , задающий limit частей
    // он будет здесь умеснее
    executionCommand = commandProvider.getCommand(commandName);

    String response;
    response = executionCommand.execute(request);
    return response;
  }
}
