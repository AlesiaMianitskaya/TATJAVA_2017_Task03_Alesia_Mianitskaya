package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.News;
import com.epam.catalog.dao.NewsDAO;

import com.epam.catalog.dao.exception.DAOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implementation of requests to the catalog
 * catalog is xml-file contains written news
 */
public class FileNewsDAO implements NewsDAO {
  private final String PATH = ".\\catalog.xml";
  private final String CATEGORY = "category";
  private final String CATEGORY_NAME = "name";
  private final String TITLE = "title";
  private final String AUTHOR = "author";
  private final String DATE = "date";

  /**
   * add news to the catalog
   *
   * @param news added news
   * @return true if news added otherwise DAOException
   * @throws DAOException exception when problem with xml-file
   */
  @Override
  public boolean addNews(News news) throws DAOException {
    if (validateAddedNews(news)) {
      throw new DAOException("It is invalid to add news.");
    }
    boolean resultAdd;
    try {
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document = documentBuilder.parse(PATH);
      Node root = document.getDocumentElement();

      Element eCategory = document.createElement(CATEGORY);
      root.appendChild(eCategory);
      eCategory.setAttribute(CATEGORY_NAME, news.getCategory());

      Element eTitle = document.createElement(TITLE);
      eTitle.setTextContent(news.getTitle());
      eCategory.appendChild(eTitle);

      Element eAuthor = document.createElement(AUTHOR);
      eAuthor.setTextContent(news.getAuthor());
      eCategory.appendChild(eAuthor);

      Element eDate = document.createElement(DATE);
      eDate.setTextContent(news.getDate());
      eCategory.appendChild(eDate);
      write(document);
      resultAdd = true;
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new DAOException("Problem with catalog.");
    }
    return resultAdd;
  }

  /**
   * write added news to file contains catalog
   *
   * @param document tree DOM document from xml-file
   * @throws DAOException exception when problem with xml-file
   */
  private void write(Document document) throws DAOException {
    try {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(document);
      FileOutputStream out = new FileOutputStream(PATH);
      StreamResult result = new StreamResult(out);
      transformer.transform(source, result);
    } catch (TransformerException | FileNotFoundException e) {
      throw new DAOException();
    }
  }

  /**
   * validate added news of null and emptiness
   *
   * @param news added news
   * @return true if news valid otherwise false
   */
  private boolean validateAddedNews(News news) {
    boolean notCategory = news.getCategory() == null || news.getCategory().isEmpty();
    boolean notTitle = news.getTitle() == null || news.getTitle().isEmpty();
    boolean notAuthor = news.getAuthor() == null || news.getAuthor().isEmpty();
    boolean notDate = news.getDate() == null || news.getDate().isEmpty();
    return notCategory || notTitle || notAuthor || notDate;
  }

  /**
   * find news in catalog by category
   *
   * @param byCategory category by which looking for
   * @return foundList contains found news
   * if news not found throws DAOException
   * @throws DAOException exception when problem with xml-file
   */
  @Override
  public ArrayList<News> findByCategory(String byCategory) throws DAOException {
    ArrayList<News> foundList = new ArrayList<News>();
    Document document = setDocument();
    NodeList nodeList = document.getElementsByTagName(CATEGORY);
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node nNode = nodeList.item(i);
      Element element = (Element) nNode;
      String category = element.getAttribute(CATEGORY_NAME);
      String title = element.getElementsByTagName(TITLE).item(0).getTextContent();
      String author = element.getElementsByTagName(AUTHOR).item(0).getTextContent();
      String date = element.getElementsByTagName(DATE).item(0).getTextContent();
      if (category.equals(byCategory)) {
        foundList.add(new News(category, title, author, date));
      }
    }
    printFound(foundList);
    return foundList;
  }

  /**
   * find news in catalog by title
   *
   * @param byTitle title by which looking for
   * @return foundList contains found news if news not found
   * throws DAOException
   * @throws DAOException exception when problem with xml-file
   */
  @Override
  public ArrayList<News> findByTitle(String byTitle) throws DAOException {
    ArrayList<News> foundList = new ArrayList<News>();
    Document document = setDocument();
    NodeList nodeList = document.getElementsByTagName(CATEGORY);
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node nNode = nodeList.item(i);
      Element element = (Element) nNode;
      String category = element.getAttribute(CATEGORY_NAME);
      String title = element.getElementsByTagName(TITLE).item(0).getTextContent();
      String author = element.getElementsByTagName(AUTHOR).item(0).getTextContent();
      String date = element.getElementsByTagName(DATE).item(0).getTextContent();
      if (title.equals(byTitle)) {
        foundList.add(new News(category, title, author, date));
      }
    }
    printFound(foundList);
    return foundList;
  }

  /**
   * find news in catalog by author
   *
   * @param byAuthor author's name by which looking for
   * @return foundList contains found news
   * if news not found throws DAOException
   * @throws DAOException exception when problem with xml-file
   */
  @Override
  public ArrayList<News> findByAuthor(String byAuthor) throws DAOException {
    ArrayList<News> foundList = new ArrayList<>();
    Document document = setDocument();
    NodeList nodeList = document.getElementsByTagName(CATEGORY);
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node nNode = nodeList.item(i);
      Element element = (Element) nNode;
      String category = element.getAttribute(CATEGORY_NAME);
      String title = element.getElementsByTagName(TITLE).item(0).getTextContent();
      String author = element.getElementsByTagName(AUTHOR).item(0).getTextContent();
      String date = element.getElementsByTagName(DATE).item(0).getTextContent();
      if (author.equals(byAuthor)) {
        foundList.add(new News(category, title, author, date));
      }
    }
    printFound(foundList);
    return foundList;
  }

  /**
   * find specific news in catalog
   *
   * @param news news by which looking for
   * @return foundList contains found news if news not found
   * return empty arrayList
   * @throws DAOException exception when problem with xml-file
   */
  public ArrayList<News> findByNews(News news) throws DAOException {
    ArrayList<News> foundList = new ArrayList<News>();
    Document document = setDocument();
    NodeList nodeList = document.getElementsByTagName(CATEGORY);
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node nNode = nodeList.item(i);
      Element element = (Element) nNode;
      String category = element.getAttribute(CATEGORY_NAME);
      String title = element.getElementsByTagName(TITLE).item(0).getTextContent();
      String author = element.getElementsByTagName(AUTHOR).item(0).getTextContent();
      String date = element.getElementsByTagName(DATE).item(0).getTextContent();
      News catalogNews = new News(category, title, author, date);
      if (news.equals(catalogNews)) {
        foundList.add(catalogNews);
      }
    }
    printFound(foundList);
    return foundList;
  }

  /**
   * create tree DOM document from xml-file
   *
   * @return document
   * @throws DAOException exception when problem with xml-file
   */
  private Document setDocument() throws DAOException {
    Document document;
    try {
      File inputFile = new File(PATH);
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      document = builder.parse(inputFile);
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new DAOException("Problem with catalog.");
    }
    return document;
  }

  /**
   * print found news
   *
   * @param foundList arrayList contains found news
   * @throws DAOException exception when the news is not found
   */
  private static void printFound(ArrayList<News> foundList) throws DAOException {
    if (foundList.isEmpty()) {
      throw new DAOException();
    } else {
      System.out.println("\nFound " + foundList.size() + " news:");
      for (int i = 0; i < foundList.size(); i++) {
        System.out.println(foundList.get(i).getCategory() + " " + foundList.get(i).getTitle() + " "
            + foundList.get(i).getAuthor() + " " + foundList.get(i).getDate());
      }
    }
  }
}
