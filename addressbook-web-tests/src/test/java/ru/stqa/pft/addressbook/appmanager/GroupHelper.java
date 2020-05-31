package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver webDriver) {
    super(webDriver);
  }

  // Создание группы
  public void create(GroupData group) {
    initGroupCreation();
    fillForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  // Изменение группы
  public void modify(GroupData group) {
    selectById(group.getId());
    initGroupModification();
    fillForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  // Удаление группы
  public void delete(GroupData group) {
    selectById(group.getId());
    deleteSelectedGroup();
    groupCache = null;
    returnToGroupPage();
  }

  // Инициировать создание группы
  public void initGroupCreation() {
    click(By.name("new"));
  }

  // Заполнить информацию о группе
  public void fillForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  // Подтвердить создание группы
  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  // Выбрать группу
  public void selectById(int id) {
    webDriver.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return webDriver.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  public Groups all() {
    if (groupCache != null) {
      return new Groups(groupCache);
    }
    groupCache = new Groups();
    List<WebElement> elements = webDriver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

}


