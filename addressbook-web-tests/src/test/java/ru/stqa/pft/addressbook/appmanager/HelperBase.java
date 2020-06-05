package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class HelperBase {
  protected WebDriver webDriver;

  public HelperBase(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void click(By locator) {
    webDriver.findElement(locator).click();
  }

  public void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = webDriver.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        webDriver.findElement(locator).clear();
        webDriver.findElement(locator).sendKeys(text);
      }
    }
  }

  public void attach(By locator, File file) {
    if (file != null) {
        webDriver.findElement(locator).sendKeys(file.getAbsolutePath());
      }
  }
  public void accept() {
    webDriver.switchTo().alert().accept();
    new WebDriverWait(webDriver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.msgbox")));
  }

  public void select(By locator, String text) {
    new Select(webDriver.findElement(locator)).selectByVisibleText(text);
  }

  protected boolean isElementPresent(By locator) {
    try {
      webDriver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

}
