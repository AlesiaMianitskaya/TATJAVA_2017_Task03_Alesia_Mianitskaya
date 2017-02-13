package com.epam.catalog.service.impl;

import com.epam.catalog.bean.News;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.NewsService;
import com.epam.catalog.service.exception.ServiceException;

/**
 * Implementation requests. Checking input data for DAO
 */
public class NewsServiceImpl implements NewsService {
  /**
   * check input data and add news to the catalog
   *
   * @param news added news to the catalog
   * @throws ServiceException for incorrect input data
   */
  @Override
  public void addNews(News news) throws ServiceException {
    boolean notCategory = news.getCategory().isEmpty() || (news.getCategory() == null);
    boolean notTitle = news.getTitle().isEmpty() || (news.getTitle() == null);
    boolean notAuthor = news.getAuthor().isEmpty() || (news.getAuthor() == null);
    boolean notDate = news.getDate().isEmpty() || (news.getDate() == null);

    if (notCategory) {// логика выполнения кода
      // например, каталог пришел пустым => метод сгенерирует исключительную ситуацию
      // ? - зачем было тратить время на другие проверки?
      throw new ServiceException("News' category is not valid.");
    }
    if (notTitle) {
      throw new ServiceException("News' title is not valid.");
    }
    if (notAuthor) {
      throw new ServiceException("News' author is not valid.");
    }
    if (notDate) {
      throw new ServiceException("News' date is not valid.");
    }

    try {
      DAOFactory daoFactoryObj = DAOFactory.getInstance();
      NewsDAO newsDAO = daoFactoryObj.getNewsDAO();
      newsDAO.addNews(news);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  /**
   * check input data and find news by category
   *
   * @param category found news' title
   * @throws ServiceException for incorrect input data
   */
  @Override
  public void findByCategory(String category) throws ServiceException{
    if (category == null || category.isEmpty()) {
      throw new ServiceException("Comparison condition is not valid.");
    }
    try {
      DAOFactory daoFactoryObj = DAOFactory.getInstance();
      NewsDAO newsDAO = daoFactoryObj.getNewsDAO();
      newsDAO.findByCategory(category);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  /**
   * check input data and find news by title
   *
   * @param title found news' title
   * @throws ServiceException for incorrect input data
   */
  @Override
  public void findByTitle(String title) throws ServiceException {
    if (title == null || title.isEmpty()) {
      throw new ServiceException("Comparison condition is not valid.");
    }
    try {
      DAOFactory daoFactoryObj = DAOFactory.getInstance();
      NewsDAO newsDAO = daoFactoryObj.getNewsDAO();
      newsDAO.findByTitle(title);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  /**
   * check input data and find news by author
   *
   * @param author found news' author
   * @throws ServiceException for incorrect input data
   */
  @Override
  public void findByAuthor(String author) throws ServiceException {
    if (author == null || author.isEmpty()) {
      throw new ServiceException("Comparison condition is not valid.");
    }
    try {
      DAOFactory daoFactoryObj = DAOFactory.getInstance();
      NewsDAO newsDAO = daoFactoryObj.getNewsDAO();
      newsDAO.findByAuthor(author);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }

  /**
   * check input data and find specific news
   *
   * @param news found news
   * @throws ServiceException for incorrect input data
   */
  @Override
  public void findByNews(News news) throws ServiceException {
    boolean notCategory = news.getCategory().isEmpty() || (news.getCategory() == null);
    boolean notTitle = news.getTitle().isEmpty() || (news.getTitle() == null);
    boolean notAuthor = news.getAuthor().isEmpty() || (news.getAuthor() == null);
    boolean notDate = news.getDate().isEmpty() || (news.getDate() == null);

    if (notCategory) {
      throw new ServiceException("News' category is not valid.");
    }
    if (notTitle) {
      throw new ServiceException("News' title is not valid.");
    }
    if (notAuthor) {
      throw new ServiceException("News' author is not valid.");
    }
    if (notDate) {
      throw new ServiceException("News' date is not valid.");
    }
    try {
      DAOFactory daoFactoryObj = DAOFactory.getInstance();
      NewsDAO newsDAO = daoFactoryObj.getNewsDAO();
      newsDAO.findByNews(news);
    } catch (DAOException e) {
      throw new ServiceException(e);
    }
  }
}
