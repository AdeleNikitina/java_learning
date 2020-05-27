package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver webDriver) {
    super(webDriver);
  }

  // Инициировать создание нового контакта
  public void initCreateContact() {
   click(By.linkText("add new"));
  }

  // Заполнение ФИО пользователя
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
  public void saveContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  // Переход на страницу редактирования контакта
  public void editContact(int index) {
    int i = index + 1;
    click(By.xpath("(//img[@alt='Edit'])[" + i + "]"));
  }

  // Обновить контакт
  public void updateContact() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  // Выбор первого контакта из списка
  public void selectContact(int index) {
    webDriver.findElements(By.name("selected[]")).get(index).click();
  }

  // Удалить контакт через страницу редактирования контакта
  public void deleteContact() {
    click(By.xpath("(//input[@value='Delete'])"));
  }

  // Потверждение удаления контакта
  public void acceptDeletionContact() {
    accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList(){
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = webDriver.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
