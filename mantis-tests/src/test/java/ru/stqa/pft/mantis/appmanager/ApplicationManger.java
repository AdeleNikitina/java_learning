package ru.stqa.pft.mantis.appmanager;

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
  private WebDriver webDriver;
  private final Properties properties;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private NavigationHelper navigationHelper;
  private UISession uiSession;
  private SoapHelper soapHelper;
  private DbHelper dbHelper;

  public ApplicationManger(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target","local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

   public void stop() {
    if (webDriver != null) {
      webDriver.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp =  new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper =  new MailHelper(this);
    }
    return mailHelper;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper =  new JamesHelper(this);
    }
    return jamesHelper;
  }

  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper =  new SoapHelper(this);
    }
    return soapHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }

  public WebDriver getDriver() {
    if (webDriver == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        webDriver = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        webDriver = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        webDriver = new InternetExplorerDriver();
      }
      webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
      webDriver.get(properties.getProperty("web.baseUrl"));
    }
    return webDriver;
  }

  public NavigationHelper goTo() {
    if (navigationHelper == null) {
      navigationHelper =  new NavigationHelper(this);
    }
    return navigationHelper;
  }

  public UISession uiSession() {
    if (uiSession == null) {
      uiSession =  new UISession(this);
    }
    return uiSession;
  }

}
