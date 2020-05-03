package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.BirthdayInfo;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.PersonalData;

public class PersonHelper extends HelperBase {

  public PersonHelper (WebDriver webDriver) {
    super(webDriver);
  }

  // Инициировать создание нового контакта
  public void createPerson() {
   click(By.linkText("add new"));
  }

  // Заполнение ФИО пользователя
  public void addPersonalInfo(PersonalData personalData) {
    type(By.name("firstname"), personalData.getFirstname());
    type(By.name("middlename"), personalData.getMiddlename());
    type(By.name("lastname"), personalData.getLastname());
  }

  // Добавить прозвище
  public void addNickname(String nickname) {
    type(By.name("nickname"), nickname);
  }

  // Добавить контактные данные
  public void addContactInfo(ContactData contactData) {
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
  }

  // Добавить информацию о дне рождения
  public void addBirthdayInfo(BirthdayInfo birthdayInfo) {
    new Select(webDriver.findElement(By.name("bday"))).selectByVisibleText(birthdayInfo.getBday());
    new Select(webDriver.findElement(By.name("bmonth"))).selectByVisibleText(birthdayInfo.getBmonth());
    type(By.name("byear"), birthdayInfo.getByear());
  }

  // Сохранить контакт
  public void savePerson() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }
}
