package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonalData;

public class PersonDeletionTestsFromMenu extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("TestGroup1", null, null));
    app.getNavigationHelper().goToHomePageFromMenu();
  }

  // Удаление через главную страницу
  @Test
  public void testPersonDeletionFromMenu() throws Exception {
    if (! app.getPersonHelper().isThereAPerson())
    {
      app.getPersonHelper().initCreatePerson();
      app.getPersonHelper().fillPersonFullData(new PersonalData("FirstName", "MiddleName", "LastName", "TestGroup1"), true,
              new ContactData("Address 10", "79000000000", "test@test.ru"),
              new BirthdayInfo("1","January", "1980"));
      app.getPersonHelper().savePerson();
      app.getNavigationHelper().goToHomePage();
    }
    app.getPersonHelper().selectContact();
    app.getPersonHelper().deletePerson();
    app.getPersonHelper().acceptDeletionContact();
    app.getNavigationHelper().goToHomePageFromMenu();
  }

}
