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

public class RemoveContactFromGroupTests extends TestBase {
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
  public void removeContactToGroup() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData removedContact = null;
    GroupData removedGroup = null;

    for (ContactData contact : contacts) {
      Groups contactGroups = contact.getGroups();
      if (contactGroups.size() == 0) {
        GroupData groupAdd = groups.iterator().next();
        app.contact().addContactToGroup(contact, groupAdd);
        contactGroups.add(groupAdd);
        app.goTo().HomePageFromMenu();
      }
      for (GroupData group : groups) {
        if (contactGroups.contains(group)) {
          removedGroup = group;
          removedContact = contact;
        }
      }
    }

    app.goTo().HomePageFromMenu();
    app.contact().remoteContactToGroup(removedGroup, removedContact);
    ContactData finalRemovedContact = removedContact;
    Groups after = app.db().contacts().stream().filter((s) -> s.equals(finalRemovedContact)).findFirst().get().getGroups();;
    assertThat(removedContact.getGroups(), equalTo(after));

  }
}
