package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.PersonalData;

public class PersonModificationTests extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("TestGroup1", null, null));
    app.getNavigationHelper().goToHomePageFromMenu();
  }
  
  @Test
  public void testPersonModification() throws Exception {
    if (! app.getPersonHelper().isThereAPerson())
    {
      app.getPersonHelper().initCreatePerson();
      app.getPersonHelper().fillPersonFullData(new PersonalData("FirstName", "MiddleName", "LastName", "TestGroup1"), true,
              new ContactData("Address 10", "79000000000", "test@test.ru"),
              new BirthdayInfo("1","January", "1980"));
      app.getPersonHelper().savePerson();
      app.getNavigationHelper().goToHomePage();
    }
    app.getPersonHelper().editPerson();
    app.getPersonHelper().fillPersonFullData(new PersonalData("FirstName_new", "MiddleName_new", "LastName_new", null), false,
                                                  new ContactData("Address 19", "79000000001", "test_new@test.ru"),
                                                  new BirthdayInfo("1","January", "1980"));
    app.getPersonHelper().updatePerson();
    app.getNavigationHelper().goToHomePage();
  }

}
