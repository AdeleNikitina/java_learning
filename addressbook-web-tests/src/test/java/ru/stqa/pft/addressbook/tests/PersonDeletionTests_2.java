package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PersonDeletionTests_2 extends TestBase {
  private WebDriver webDriver;

  // Удаление через главную страницу
  @Test
  public void testPersonDeletion_2() throws Exception {
    app.getPersonHelper().selectContact();
    app.getPersonHelper().deletePerson();
    app.getPersonHelper().acceptDeletionContact();
    app.getNavigationHelper().goToHomePageFromMenu();
  }

}
