package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.PersonalData;

public class PersonCreationTest extends TestBase {

  @Test
  public void testPersonCreation() throws Exception {
    app.getPersonHelper().createPerson();
    app.getPersonHelper().fillPersonalInfo(new PersonalData("FirstName", "MiddleName", "LastName", "TestGroup1"), true);
    app.getPersonHelper().fillNickname("Nickname");
    app.getPersonHelper().fillContactInfo(new ContactData("Address 10", "79000000000", "test@test.ru"));
    app.getPersonHelper().fillBirthdayInfo(new BirthdayInfo("1","January", "1980"));
    app.getPersonHelper().savePerson();
    app.getNavigationHelper().goToHomePage();
  }

}
