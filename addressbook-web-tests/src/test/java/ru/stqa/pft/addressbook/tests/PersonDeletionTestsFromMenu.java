package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PersonDeletionTestsFromMenu extends TestBase {
  private WebDriver webDriver;

  // Удаление через главную страницу
  @Test
  public void testPersonDeletionFromMenu() throws Exception {
    app.getPersonHelper().selectContact();
    app.getPersonHelper().deletePerson();
    app.getPersonHelper().acceptDeletionContact();
    app.getNavigationHelper().goToHomePageFromMenu();
  }

}
