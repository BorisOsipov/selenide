package com.codeborne.selenide;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestSecurity {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      String test = "";
      System.out.print(test);
    } catch (NullPointerException ex) {
      // BAD: printing a stack trace back to the response
      ex.printStackTrace(response.getWriter());
    }
  }
}
