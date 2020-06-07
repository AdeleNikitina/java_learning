package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").
              withGroup("TestGroup1").
              withAddress("Address 10").withMobile("79000000000").withEmail("test@test.ru"));
      app.goTo().HomePage();
    }
  }
  
  @Test
  public void testContactModification() throws Exception {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).
            withFirstname("FirstName_new").withMiddlename("MiddleName_new").withLastname("LastName_new").
            withGroup("TestGroup1").
            withAddress("Address 10").withMobile("79000000000").withEmail("test@test.ru");
    app.contact().modify(contact);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
