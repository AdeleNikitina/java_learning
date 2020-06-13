package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  
  private final ApplicationManger app;
  private WebDriver webDriver;

  public RegistrationHelper(ApplicationManger app) {
    this.app = app;
    webDriver = app.getDriver();
  }

  public void start(String username, String email) {
    webDriver.get(app.getProperty("web.baseUrl") + "signup_page.php");
  }
}
