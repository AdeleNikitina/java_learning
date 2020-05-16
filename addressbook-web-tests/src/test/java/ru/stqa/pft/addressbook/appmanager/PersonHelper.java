package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.PersonalData;

public class PersonHelper extends HelperBase {

  public PersonHelper (WebDriver webDriver) {
    super(webDriver);
  }

  // Инициировать создание нового контакта
  public void initCreatePerson() {
   click(By.linkText("add new"));
  }

  // Заполнение ФИО пользователя
  public void fillPersonalInfo(PersonalData personalData, boolean creation) {
    type(By.name("firstname"), personalData.getFirstname());
    type(By.name("middlename"), personalData.getMiddlename());
    type(By.name("lastname"), personalData.getLastname());
    if (creation) {
      select(By.name("new_group"), personalData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  // Добавить прозвище
  public void fillNickname(String nickname) {
    type(By.name("nickname"), nickname);
  }

  // Добавить контактные данные
  public void fillContactInfo(ContactData contactData) {
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
  }

  // Добавить информацию о дне рождения
  public void fillBirthdayInfo(BirthdayInfo birthdayInfo) {
    select(By.name("bday"), birthdayInfo.getBday());
    select(By.name("bmonth"), birthdayInfo.getBmonth());
    type(By.name("byear"), birthdayInfo.getByear());
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

  // Заполнение данных пользователя
  public void fillPersonFullData(PersonalData personalData, boolean b, ContactData contactData, BirthdayInfo birthdayInfo) {
    fillPersonalInfo(personalData, b);
    fillNickname("Nickname");
    fillContactInfo(contactData);
    fillBirthdayInfo(birthdayInfo);
  }

  public boolean isThereAPerson() {
    return isElementPresent(By.name("selected[]"));
  }
}
