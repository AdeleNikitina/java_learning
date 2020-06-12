package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
  private Properties properties;

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname(properties.getProperty("web.firstName")).withMiddlename(properties.getProperty("web.middleName")).withLastname(properties.getProperty("web.lastName"))
              .withGroup(properties.getProperty("web.testGroupName"))
              .withAddress(properties.getProperty("web.address")).withMobile(properties.getProperty("web.mobile")).withEmail(properties.getProperty("web.email")));
      app.goTo().HomePage();
    }
  }
  
  @Test
  public void testContactModification() throws Exception {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId())
             .withFirstname(properties.getProperty("web.firstName")).withMiddlename(properties.getProperty("web.middleName")).withLastname(properties.getProperty("web.lastName"))
            .withGroup(properties.getProperty("web.testGroupName"))
            .withAddress(properties.getProperty("web.address")).withMobile(properties.getProperty("web.mobile")).withEmail(properties.getProperty("web.email"));
    app.contact().modify(contact);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
