package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTestsFromEdit extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName")
              .withGroup("TestGroup1")
              .withAddress("Address 10").withMobile("79000000000").withEmail("test@test.ru"));
      app.goTo().HomePage();
    }
  }

  // Удаление через страницу редактирования контакта
  @Test
  public void testContactDeletionFromEdit() throws Exception {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromEdit(deletedContact);
    app.goTo().HomePageFromMenu();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
