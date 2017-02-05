package com.epam.catalog.dao;

import com.epam.catalog.bean.News;
import com.epam.catalog.dao.exception.DAOException;

import java.util.ArrayList;

/**
 * Interface for requests to the catalog
 */
public interface NewsDAO {
  boolean addNews(News news) throws DAOException;

  ArrayList<News> findByTitle(String title) throws DAOException;

  ArrayList<News> findByAuthor(String author) throws DAOException;

  ArrayList<News> findByNews(News news) throws DAOException;

  ArrayList<News> findByCategory(String category) throws  DAOException;
}
