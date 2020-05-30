package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTestsFromMenu extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create();
      app.goTo().HomePage();
    }
  }

  // Удаление через главную страницу
  @Test
  public void testContactDeletionFromMenu() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().selectContact(index);
    app.contact().deleteContact();
    app.contact().acceptDeletionContact();
    app.goTo().HomePageFromMenu();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after, before);
  }

}
