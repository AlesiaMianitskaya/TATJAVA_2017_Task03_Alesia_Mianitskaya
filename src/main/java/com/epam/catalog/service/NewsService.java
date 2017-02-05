package com.epam.catalog.service;

import com.epam.catalog.bean.News;
import com.epam.catalog.service.exception.ServiceException;

/**
 * Interface for requests to the catalog
 */
public interface NewsService {
  void addNews(News news) throws ServiceException;

  void findByCategory(String category) throws ServiceException;

  void findByTitle(String title) throws ServiceException;

  void findByAuthor(String author) throws ServiceException;

  void findByNews(News news) throws ServiceException;
}
