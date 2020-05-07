package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver webDriver) {
    super(webDriver);
  }

  public void goToGroupPage(String groups) {
    click(By.linkText(groups));
  }

  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void goToHomePageFromMenu() {
  click(By.xpath("//a[contains(text(),'home')]"));
  }

}
