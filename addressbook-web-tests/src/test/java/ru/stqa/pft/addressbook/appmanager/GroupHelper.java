package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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
  public void selectGroup(int index) {
    webDriver.findElements(By.name("selected[]")).get(index).click();
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

  // Создание группы
  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void modifyGroup(int index, GroupData group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return webDriver.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = webDriver.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }
}


