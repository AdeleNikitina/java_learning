package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver webDriver) {
    super(webDriver);
  }

  // Создание контакта
  public void create() {
    initCreateContact();
    fillContactInfo(new ContactData().
            withFirstname("FirstName").withMiddlename("MiddleName").withLastname("LastName").
            withGroup("TestGroup1").
            withAddress("Address 10").withMobile("79000000000").withEmail("test@test.ru"), true);
    save();
    contactCache = null;
  }

  // Редактирование контакта
  public void modify(ContactData contact) {
    editContact(contact.getId());
    fillContactInfo(contact, false);
    fillNickname("Nickname");
    updateContact();
    contactCache = null;
  }

  // Удаление контакта через основное меню
  public void deleteFromMenu(ContactData contact) {
    selectContact(contact.getId());
    deleteContact();
    acceptDeletionContact();
    contactCache = null;
  }

  // Удаление контакта через редактирование
  public void deleteFromEdit(ContactData contact) {
    editContact(contact.getId());
    deleteContact();
    contactCache = null;
  }

  // Клик по кнопке
  public void deleteContact() {
    click(By.xpath("(//input[@value='Delete'])"));
  }

  // Инициировать создание нового контакта
  public void initCreateContact() {
   click(By.linkText("add new"));
  }

  // Заполнение ФИО пользователя и контактных данных
  public void fillContactInfo(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      select(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  // Добавить прозвище
  public void fillNickname(String nickname) {
    type(By.name("nickname"), nickname);
  }

  // Сохранить контакт
  public void save() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  // Переход на страницу редактирования контакта
  public void editContact(int id) {
    int i = id;
    webDriver.findElement(By.xpath("(//img[@alt='Edit'])['" + id + "']")).click();
  }

  // Обновить контакт
  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  // Выбор первого контакта из списка
  public void selectContact(int id) {
    webDriver.findElement(By.cssSelector("input[id ='" + id + "']")).click();
  }

  // Потверждение удаления контакта
  public void acceptDeletionContact() {
    accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return webDriver.findElements(By.name("selected[]")).size();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = webDriver.findElement(By.name("firstname")).getAttribute("value");
    String lastname = webDriver.findElement(By.name("lastname")).getAttribute("value");
    String home = webDriver.findElement(By.name("home")).getAttribute("value");
    String mobile = webDriver.findElement(By.name("mobile")).getAttribute("value");
    String work = webDriver.findElement(By.name("work")).getAttribute("value");
    webDriver.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobile(mobile).withWorkPhone(work);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = webDriver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

//    webDriver.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//    webDriver.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
//    webDriver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

  private Contacts contactCache = null;

  public Contacts all(){
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = webDriver.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withHomePhone(phones[0]).withMobile(phones[1]).withWorkPhone(phones[2]));
    }
    return new Contacts(contactCache);
  }

}
