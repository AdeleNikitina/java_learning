package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("TestGroup1", null, null));
    app.getNavigationHelper().goToHomePageFromMenu();
  }
  
  @Test
  public void testPersonModification() throws Exception {
    if (! app.getContactHelper().isThereAContact())
    {
      app.getContactHelper().initCreateContact();
      app.getContactHelper().fillContactInfo(new ContactData
                                            ("FirstName", "MiddleName", "LastName", "TestGroup1",
                                             "Address 10", "79000000000", "test@test.ru"), true);
      app.getContactHelper().saveContact();
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"FirstName_new", "MiddleName_new", "LastName_new", "TestGroup1",
                    "Address 10", "79000000000", "test@test.ru");
    app.getContactHelper().fillContactInfo(contact, false);
    app.getContactHelper().fillNickname("Nickname");
    app.getContactHelper().updateContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
