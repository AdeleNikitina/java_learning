package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManger {
  public WebDriver webDriver;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private PersonHelper personHelper;

  public void init() {
    webDriver = new FirefoxDriver();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    webDriver.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(webDriver);
    navigationHelper = new NavigationHelper(webDriver);
    personHelper = new PersonHelper(webDriver);
    sessionHelper = new SessionHelper(webDriver);
    sessionHelper.login("admin", "secret");
  }

   public void stop() {
    webDriver.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public PersonHelper getPersonHelper() {
    return personHelper;
  }

}
