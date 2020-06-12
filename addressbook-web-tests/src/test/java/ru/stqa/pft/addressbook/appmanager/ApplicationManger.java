package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManger {
  public WebDriver webDriver;
  private final Properties properties;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManger(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target","local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

    if (browser.equals(BrowserType.FIREFOX)) {
      webDriver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      webDriver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      webDriver = new InternetExplorerDriver();
    }
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    webDriver.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(webDriver);
    navigationHelper = new NavigationHelper(webDriver);
    contactHelper = new ContactHelper(webDriver);
    sessionHelper = new SessionHelper(webDriver);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
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

  public DbHelper db() {
    return dbHelper;
  }
}
