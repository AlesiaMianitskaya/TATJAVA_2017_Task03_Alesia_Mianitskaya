package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.News;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Unit tests for class FileNewsDAO
 */
public class FileNewsDAOTest {
  DAOFactory daoFactoryObj;
  private NewsDAO newsDAO;
  private News news;
  private ArrayList<News> foundList;

  @BeforeTest
  public void setUp() throws Exception {
    daoFactoryObj = DAOFactory.getInstance();
    newsDAO = daoFactoryObj.getNewsDAO();
  }

  @AfterTest
  public void tearDown() throws Exception {
    daoFactoryObj = null;
    newsDAO = null;
  }

  @Test
  public void testAddNewsPositive() throws Exception {
    String category = "Book";
    String title = "Atlas Shrugged";
    String author = "Ayn Rand";
    String date = "01.18.2017";
    news = new News(category, title, author, date);
    assertTrue(newsDAO.addNews(news));
  }

  @Test
  public void testFindByTitlePositive() throws Exception {
    String title = "La La Land";
    String category = "Film";
    String author = "Damien Chazelle";
    String date = "01.15.2017";
    foundList = newsDAO.findByTitle(title);
    assertEquals(new News(category, title, author, date), foundList.get(0));
  }

  @Test(expectedExceptions = DAOException.class)
  public void testFindByTitleEmpty() throws Exception {
    newsDAO.findByTitle("");
  }

  @Test(expectedExceptions = DAOException.class)
  public void findByTitleNull() throws Exception {
    newsDAO.findByTitle(null);
  }

  @Test
  public void testFindByAuthorPositive() throws Exception {
    String category = "Book";
    String title = "The Fountainhead";
    String author = "Ayn Rand";
    String date = "01.18.2017";
    foundList = newsDAO.findByAuthor(author);
    assertEquals(new News(category, title, author, date), foundList.get(0));
  }

  @Test(expectedExceptions = DAOException.class)
  public void testFindByAuthorEmpty() throws Exception {
    newsDAO.findByAuthor("");
  }

  @Test(expectedExceptions = DAOException.class)
  public void testFindByAuthorNull() throws Exception {
    newsDAO.findByAuthor(null);
  }

  @Test
  public void testFindByCategoryPositive() throws Exception{
    String category = "Film";
    foundList = newsDAO.findByCategory(category);
    assertEquals(category, foundList.get(0).getCategory());
  }

  @Test(expectedExceptions = DAOException.class)
  public void testFindByCategoryEmpty() throws Exception {
    newsDAO.findByCategory("");
  }

  @Test(expectedExceptions = DAOException.class)
  public void testFindByCategoryNull() throws Exception {
    newsDAO.findByAuthor(null);
  }

  @Test
  public void testFindByNewsPositive() throws Exception {
    String category = "Book";
    String title = "The Fountainhead";
    String author = "Ayn Rand";
    String date = "01.18.2017";
    news = new News(category, title, author, date);
    foundList = newsDAO.findByNews(news);
    assertEquals(news, foundList.get(0));
  }

  @DataProvider(name = "incorrect data")
  public Object[][] getWrongNews() {
    return new Object[][]{
        {new News(null, null, null, null)},
        {new News(null, "The Fountainhead", "Ayn Rand", "01.18.2017")},
        {new News("Book", null, "Ayn Rand", "01.18.2017")},
        {new News("Book", "The Fountainhead", null, "01.18.2017")},
        {new News("Book", "The Fountainhead", "Ayn Rand", null)},
        {new News("", "", "", "")},
        {new News("", "The Fountainhead", "Ayn Rand", "01.18.2017")},
        {new News("Book", "", "Ayn Rand", "01.18.2017")},
        {new News("Book", "The Fountainhead", "", "01.18.2017")},
        {new News("Book", "The Fountainhead", "Ayn Rand", "")}
    };
  }

  @Test(expectedExceptions = DAOException.class, dataProvider = "incorrect data")
  public void testFindByNewsException(News wrongNews) throws Exception {
    foundList = newsDAO.findByNews(wrongNews);
  }

  @Test(expectedExceptions = DAOException.class, dataProvider = "incorrect data")
  public void testAddNewsException(News wrongNews) throws Exception{
    newsDAO.addNews(wrongNews);
  }
}