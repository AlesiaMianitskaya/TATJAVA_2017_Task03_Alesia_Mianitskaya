package com.epam.catalog.command.impl;

import com.epam.catalog.bean.News;
import com.epam.catalog.command.Command;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

/**
 * Execution command - find by specific news
 */
public class FindByNews implements Command {
  private final String separator = " ";
  private final String separatorTitle = "\'";

  /**
   * get arguments from request and execute command
   *
   * @param request line contains specific news
   * @return response to the request
   */
  @Override
  public String execute(String request) {
    String category = request.split(separator)[1];
    String title = request.substring(request.indexOf(separatorTitle) + 1, request.lastIndexOf(separatorTitle));
    String author = request.substring(request.lastIndexOf(separatorTitle) + 2, request.lastIndexOf(separator));
    String date = request.substring(request.lastIndexOf(separator) + 1);

    String response;
    ServiceFactory service = ServiceFactory.getInstance();
    NewsService newsService = service.getNewsService();
    try {
      newsService.findByNews(new News(category, title, author, date));
      response = "It is search results.";
    } catch (ServiceException e) {
      response = "News not found.";
    }
    return response;
  }
}
