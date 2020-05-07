package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver webDriver) {
    super(webDriver);
  }

  // Инициировать создание группы
  public void initGroupCreation() {
    click(By.name("new"));
  }

  // Заполнить информацию о группе
  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  // Подтвердить создание группы
  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  // Выбрать группу
  public void selectGroup() {
    click(By.name("selected[]"));
  }

  // Удалить выбранную группу
  public void deleteSelectedGroup() {
    click(By.xpath("(//input[@name='delete'])[1]"));
  }

  // Вернуться на страницу с группами
  public void returnToGroupPage() {
    click(By.linkText("groups"));
  }

  // Инициировать изменение группы
  public void initGroupModification() {
    click(By.name("edit"));
  }

  // Подтвердить изменение группы
  public void submitGroupModification() {
    click(By.name("update"));
  }
}