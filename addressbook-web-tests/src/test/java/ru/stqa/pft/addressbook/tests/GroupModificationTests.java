package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  private Properties properties;

  @BeforeMethod
  public void ensurePreconditions() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(properties.getProperty("web.testGroupName")));
    }
  }

  @Test
  public void testGroupModification () throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().
            withId(modifiedGroup.getId())
            .withName(properties.getProperty("web.testGroupName")).withHeader(properties.getProperty("web.testGroupHeader")).withFooter(properties.getProperty("web.testGroupFooter"));
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }



}
