package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class PersonCreationTest extends TestBase {

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("TestGroup1", null, null));
  }

  @Test
  public void testPersonCreation() throws Exception {
    app.getPersonHelper().initCreatePerson();
    app.getPersonHelper().fillPersonalInfo(new ContactData
            ("FirstName", "MiddleName","LastName", "TestGroup1",
             "Address 10", "79000000000", "test@test.ru"), true);
    app.getPersonHelper().savePerson();
    app.getNavigationHelper().goToHomePage();
  }

}
