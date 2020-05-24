package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("TestGroup1", null, null));
  }

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToHomePageFromMenu();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("FirstName", null, "LastName", null, null, null, null);
    app.getContactHelper().initCreateContact();
    app.getContactHelper().fillContactInfo(new ContactData
            ("FirstName", "MiddleName","LastName", "TestGroup1",
             "Address 10", "79000000000", "test@test.ru"), true);
    app.getContactHelper().saveContact();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
