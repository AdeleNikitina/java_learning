package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTestsFromMenu extends TestBase {

  private Properties properties;

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname(properties.getProperty("web.firstName")).withMiddlename(properties.getProperty("web.middleName")).withLastname(properties.getProperty("web.lastName"))
              .inGroup(groups.iterator().next())
              .withAddress(properties.getProperty("web.address")).withMobile(properties.getProperty("web.mobile")).withEmail(properties.getProperty("web.email")));
      app.goTo().HomePage();
    }
  }

  // Удаление через главную страницу
  @Test
  public void testContactDeletionFromMenu() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromMenu(deletedContact);
    app.goTo().HomePageFromMenu();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
