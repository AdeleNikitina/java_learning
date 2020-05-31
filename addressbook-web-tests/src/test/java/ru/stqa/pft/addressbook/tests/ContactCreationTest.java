package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage("groups");
    app.group().create(new GroupData().withName("TestGroup1"));
  }

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().HomePageFromMenu();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("FirstName").withLastname("LastName");
    app.contact().create();
    app.goTo().HomePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

}
