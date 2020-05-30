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
    app.goTo().groupPage("groups");
    app.group().create(new GroupData().withName("TestGroup1"));
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().HomePageFromMenu();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("FirstName").withLastname("LastName");
    app.contact().create();
    app.goTo().HomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }

}
