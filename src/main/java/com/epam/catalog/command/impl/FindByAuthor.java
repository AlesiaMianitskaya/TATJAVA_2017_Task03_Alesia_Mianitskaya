package com.epam.catalog.command.impl;

import com.epam.catalog.command.Command;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

/**
 * Execution command - find by author
 */
public class FindByAuthor implements Command{
  private final char separator = ' ';

  /**
   * get arguments from request and execute command
   *
   * @param request line contains author's name
   * @return response to the request
   */
  @Override
  public String execute(String request) {
    String author = request.substring(request.indexOf(separator) + 1);

    ServiceFactory service = ServiceFactory.getInstance();
    NewsService newsService = service.getNewsService();

    String response;
    try {
      newsService.findByAuthor(author);
      response = "It is search results.";  ///look it
    } catch (ServiceException e) {
      response = "News not found.";
    }
    return response;
  }
}
