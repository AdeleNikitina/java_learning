package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withId(before.get(index).getId()).
            withFirstname("FirstName_new").withMiddlename("MiddleName_new").withLastname("LastName_new").
            withGroup("TestGroup1").
            withAddress("Address 10").withMobile("79000000000").withEmail("test@test.ru");
    app.contact().modify(index, contact);
    app.goTo().HomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
