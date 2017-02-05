package com.epam.catalog.view;

import com.epam.catalog.controller.Controller;

import java.util.ArrayList;

/**
 * Start point of the program
 */
public class Main {
  /**
   * Execute all requests.
   * Request is txt-file contains command's name and input arguments
   *
   * @param args command line argument
   */
  public static void main(String[] args) {
    ReaderRequest reader = new ReaderRequest();
    ArrayList<String> requestList = reader.readRequest();

    Controller controller = new Controller();
    for (int i = 0; i < requestList.size(); i++) {
      System.out.println(controller.executeGoal(requestList.get(i)));
    }
  }
}
