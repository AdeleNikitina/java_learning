package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTestsFromMenu extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create();
      app.goTo().HomePage();
    }
  }

  // Удаление через главную страницу
  @Test
  public void testContactDeletionFromMenu() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromMenu(deletedContact);
    app.goTo().HomePageFromMenu();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(after, before);
  }


}
