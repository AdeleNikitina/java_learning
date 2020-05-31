package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage("groups");
    app.group().create(new GroupData().withName("TestGroup1"));
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().HomePageFromMenu();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("FirstName").withLastname("LastName");
    app.contact().create();
    app.goTo().HomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }

}
