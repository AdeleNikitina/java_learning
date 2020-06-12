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
  public void addContactToGroup() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    // выбираем контакт, который будем добавлять в группы
    Contacts contacts = app.db().contacts();
    ContactData contact = contacts.iterator().next();
    // смотрим какие группы есть и все ли добавлены
    Groups addedGroups = contact.getGroups();
    Groups existGroups = app.db().groups();
    Groups notAddedGroups = new Groups();

    if (addedGroups == existGroups ) {
      app.goTo().groupPage();
      GroupData newGroup = new GroupData().withName(properties.getProperty("web.testGroupName"));
      app.group().create(newGroup);
      existGroups = app.db().groups();
    }

    for (GroupData group : existGroups)  {
      if (!addedGroups.contains(group)) {
        notAddedGroups.add(group);
      }
    }
    System.out.println(notAddedGroups);
    while (notAddedGroups.size() != 0) {
      GroupData groupAdd = notAddedGroups.iterator().next();
      app.contact().addContactToGroup(contact, groupAdd);
      notAddedGroups.remove(groupAdd);
      app.goTo().HomePageFromMenu();
    }
    assertThat(contact.getGroups(), equalTo(addedGroups));
  }
}

