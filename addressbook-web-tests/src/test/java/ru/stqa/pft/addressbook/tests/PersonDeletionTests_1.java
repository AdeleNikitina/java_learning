package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PersonDeletionTests_1 extends TestBase {
  private WebDriver webDriver;

  // Удаление через страницу редактирования контакта
  @Test
  public void testPersonDeletion_1() throws Exception {
    app.getPersonHelper().editPerson();
    app.getPersonHelper().deletePerson();
    app.getNavigationHelper().goToHomePageFromMenu();
  }

}
