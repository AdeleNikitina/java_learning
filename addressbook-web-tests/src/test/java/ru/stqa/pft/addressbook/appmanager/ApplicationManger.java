package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManger {
  public WebDriver webDriver;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManger(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      webDriver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      webDriver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      webDriver = new InternetExplorerDriver();
    }
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    webDriver.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(webDriver);
    navigationHelper = new NavigationHelper(webDriver);
    contactHelper = new ContactHelper(webDriver);
    sessionHelper = new SessionHelper(webDriver);
    sessionHelper.login("admin", "secret");
  }

   public void stop() {
    webDriver.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

}
