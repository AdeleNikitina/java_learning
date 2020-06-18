package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
  private Properties properties;

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    properties = new Properties();
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(properties.getProperty("web.testGroupName")));
      app.goTo().HomePageFromMenu();
    }
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname(properties.getProperty("web.firstName")).withMiddlename(properties.getProperty("web.middleName")).withLastname(properties.getProperty("web.lastName"))
              .inGroup(groups.iterator().next())
              .withAddress(properties.getProperty("web.address")).withMobile(properties.getProperty("web.mobile")).withEmail(properties.getProperty("web.email")));
      app.goTo().HomePage();
    }
  }

  @Test
  public void addContactToGroupNew() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData addedContact = null;
    GroupData addedGroup = null;

    for (ContactData contact : contacts) {
      Groups contactGroups = contact.getGroups();
      for (GroupData group : groups) {
        if (!contactGroups.contains(group)) {
          addedGroup = group;
          addedContact = contact;
        } else {
          app.goTo().groupPage();
          addedGroup = new GroupData().withName(properties.getProperty("web.testGroupName"));
          app.group().create(addedGroup);
          addedContact = contacts.iterator().next();
        }
      }
    }

    app.goTo().HomePageFromMenu();
    app.contact().addContactToGroup(addedContact, addedGroup);
    app.goTo().HomePageFromMenu();
    ContactData finalAddedContact = addedContact;
    Groups after = app.db().contacts().stream().filter((s) -> s.equals(finalAddedContact)).findFirst().get().getGroups();;
    assertThat(addedContact.getGroups(), equalTo(after));
  }
}

