package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GroupCreationTest {
  private WebDriver webDriver;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    webDriver = new FirefoxDriver();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String username, String password) {
    webDriver.get("http://localhost/addressbook/");
    webDriver.findElement(By.name("user")).clear();
    webDriver.findElement(By.name("user")).sendKeys(username);
    webDriver.findElement(By.name("pass")).clear();
    webDriver.findElement(By.name("pass")).sendKeys(password);
    webDriver.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage("groups");
    initGroupCreation();
    fillGroupForm(new GroupData("TestGroup1", "TestGroup2", "TestGroup3"));
    submitGroupCreation();
    returnToGroupPage();
  }

  private void returnToGroupPage() {
    webDriver.findElement(By.linkText("group page")).click();
  }

  private void submitGroupCreation() {
    webDriver.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupData groupData) {
    webDriver.findElement(By.name("group_name")).click();
    webDriver.findElement(By.name("group_name")).clear();
    webDriver.findElement(By.name("group_name")).sendKeys(groupData.getName());
    webDriver.findElement(By.name("group_header")).click();
    webDriver.findElement(By.name("group_header")).clear();
    webDriver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    webDriver.findElement(By.name("group_footer")).click();
    webDriver.findElement(By.name("group_footer")).clear();
    webDriver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  private void initGroupCreation() {
    webDriver.findElement(By.name("new")).click();
  }

  private void goToGroupPage(String groups) {
    webDriver.findElement(By.linkText(groups)).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    webDriver.quit();
    webDriver.findElement(By.linkText("Logout")).click();
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

