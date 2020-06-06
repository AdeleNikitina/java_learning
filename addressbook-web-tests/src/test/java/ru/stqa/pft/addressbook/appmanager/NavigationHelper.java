package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver webDriver) {
    super(webDriver);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && webDriver.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }
  }

  public void HomePage() {
    if (isElementPresent(By.id("maintable")))
    {
      return;
    } else {
      click(By.linkText("home page"));
    }
  }

  public void HomePageFromMenu() {
    if (isElementPresent(By.id("maintable")))
    {
      return;
    } else {
      click(By.xpath("//a[contains(text(),'home')]"));
    }
  }

}
