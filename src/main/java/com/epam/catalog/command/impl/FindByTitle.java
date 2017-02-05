package com.epam.catalog.command.impl;

import com.epam.catalog.command.Command;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

/**
 * Execution command - find by title
 */
public class FindByTitle implements Command {
  private final char separator = ' ';

  /**
   * get arguments from request and execute command
   *
   * @param request line contains news' title
   * @return response to the request
   */
  @Override
  public String execute(String request) {
    String title = request.substring(request.indexOf(separator) + 1);

    ServiceFactory service = ServiceFactory.getInstance();
    NewsService newsService = service.getNewsService();

    String response;
    try {
      newsService.findByTitle(title);
      response = "It is search results.";  ///see here
    } catch (ServiceException e) {
      response = "News not found.";
    }
    return response;
  }
}
