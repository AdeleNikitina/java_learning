package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    webDriver.findElement(locator).clear();
    webDriver.findElement(locator).sendKeys(text);
  }

}
