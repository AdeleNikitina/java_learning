package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.PersonalData;

public class PersonCreationTest extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.getPersonHelper().createPerson();
    app.getPersonHelper().addPersonalInfo(new PersonalData("FirstName", "MiddleName", "LastName"));
    app.getPersonHelper().addNickname("Nickname");
    app.getPersonHelper().addContactInfo(new ContactData("Address 10", "79000000000", "test@test.ru"));
    app.getPersonHelper().addBirthdayInfo(new BirthdayInfo("1","January", "1980"));
    app.getPersonHelper().savePerson();
    app.getNavigationHelper().goToHomePage();
  }

}
