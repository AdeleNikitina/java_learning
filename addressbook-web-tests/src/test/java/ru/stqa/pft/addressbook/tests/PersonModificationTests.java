package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;

public class PersonModificationTests extends TestBase {
  private WebDriver webDriver;

  @Test
  public void testPersonModification() throws Exception {
    app.getPersonHelper().editPerson();
    app.getPersonHelper().fillNickname("Nickname_new");
    app.getPersonHelper().fillContactInfo(new ContactData("Address 19", "79000000001", "test_new@test.ru"));
    app.getPersonHelper().fillBirthdayInfo(new BirthdayInfo("1","January", "1980"));
    app.getPersonHelper().updatePerson();
    app.getNavigationHelper().goToHomePage();
  }

}
