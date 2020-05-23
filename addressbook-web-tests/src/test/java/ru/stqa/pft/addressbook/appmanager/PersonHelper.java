package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class PersonHelper extends HelperBase {

  public PersonHelper (WebDriver webDriver) {
    super(webDriver);
  }

  // Инициировать создание нового контакта
  public void initCreatePerson() {
   click(By.linkText("add new"));
  }

  // Заполнение ФИО пользователя
  public void fillPersonalInfo(ContactData contactData, boolean creation) {
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
  public void savePerson() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  // Переход на страницу редактирования контакта
  public void editPerson() {
    click(By.xpath("(//img[@alt='Edit'])[1]"));
  }

  // Обновить контакт
  public void updatePerson() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  // Выбор первого контакта из списка
  public void selectContact() {
    click(By.name("selected[]"));
  }

  // Удалить контакт через страницу редактирования контакта
  public void deletePerson() {
    click(By.xpath("(//input[@value='Delete'])"));
  }

  // Потверждение удаления контакта
  public void acceptDeletionContact() {
    accept();
  }

  public boolean isThereAPerson() {
    return isElementPresent(By.name("selected[]"));
  }
}
