package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PersonCreationTest {
  private WebDriver webDriver;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    webDriver = new FirefoxDriver();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  @Test
  public void testUserCreation() throws Exception {
    createPerson();
    addPersonalInfo(new PersonalData("FirstName", "MiddleName", "LastName"));
    addNickname("Nickname");
    addContactInfo(new ContactData("Address 10", "79000000000", "test@test.ru"));
    addBirthdayInfo(new BirthdayInfo("1","January", "1980"));
    savePerson();
    goToHomePage();
    logout();
  }

  private void createPerson() {
    webDriver.findElement(By.linkText("add new")).click();
  }

  private void addPersonalInfo(PersonalData personalData) {
    webDriver.findElement(By.name("firstname")).clear();
    webDriver.findElement(By.name("firstname")).sendKeys(personalData.getFirstname());
    webDriver.findElement(By.name("middlename")).clear();
    webDriver.findElement(By.name("middlename")).sendKeys(personalData.getMiddlename());
    webDriver.findElement(By.name("lastname")).clear();
    webDriver.findElement(By.name("lastname")).sendKeys(personalData.getLastname());
  }

  private void addNickname(String nickname) {
    webDriver.findElement(By.name("nickname")).clear();
    webDriver.findElement(By.name("nickname")).sendKeys(nickname);
  }

  private void addContactInfo(ContactData contactData) {
    webDriver.findElement(By.name("address")).clear();
    webDriver.findElement(By.name("address")).sendKeys(contactData.getAddress());
    webDriver.findElement(By.name("mobile")).clear();
    webDriver.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
    webDriver.findElement(By.name("email")).clear();
    webDriver.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  private void addBirthdayInfo(BirthdayInfo birthdayInfo) {
    new Select(webDriver.findElement(By.name("bday"))).selectByVisibleText(birthdayInfo.getBday());
    new Select(webDriver.findElement(By.name("bmonth"))).selectByVisibleText(birthdayInfo.getBmonth());
    webDriver.findElement(By.name("byear")).clear();
    webDriver.findElement(By.name("byear")).sendKeys(birthdayInfo.getByear());
  }

  private void savePerson() {
    webDriver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void goToHomePage() {
    webDriver.findElement(By.linkText("home page")).click();
  }

  private void login(String username, String password) {
    webDriver.get("http://localhost/addressbook/");
    webDriver.findElement(By.name("user")).clear();
    webDriver.findElement(By.name("user")).sendKeys(username);
    webDriver.findElement(By.name("pass")).clear();
    webDriver.findElement(By.name("pass")).sendKeys(password);
    webDriver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  private void logout() {
    webDriver.findElement(By.linkText("Logout")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    webDriver.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      webDriver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      webDriver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
