package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTestsFromEdit extends TestBase {
  private WebDriver webDriver;

  @BeforeMethod
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage("groups");
    app.getGroupHelper().createGroup(new GroupData("TestGroup1", null, null));
    app.getNavigationHelper().goToHomePageFromMenu();
  }

  // Удаление через страницу редактирования контакта
  @Test
  public void testContactDeletionFromEdit() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().initCreateContact();
      app.getContactHelper().fillContactInfo(new ContactData("FirstName", "MiddleName", "LastName",
                                             "TestGroup1","Address 10", "79000000000", "test@test.ru"), true);
      app.getContactHelper().saveContact();
      app.getNavigationHelper().goToHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePageFromMenu();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }

}
