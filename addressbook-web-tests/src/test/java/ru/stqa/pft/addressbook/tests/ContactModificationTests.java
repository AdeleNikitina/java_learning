package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create();
      app.goTo().HomePage();
    }
  }
  
  @Test
  public void testPersonModification() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).
            withFirstname("FirstName_new").withMiddlename("MiddleName_new").withLastname("LastName_new").
            withGroup("TestGroup1").
            withAddress("Address 10").withMobile("79000000000").withEmail("test@test.ru");
    app.contact().modify(contact);
    app.goTo().HomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
