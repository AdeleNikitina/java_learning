package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UISession extends HelperBase {

  public UISession(ApplicationManger app) {
    super(app);
    webDriver = app.getDriver();
  }

  public void loginFromUI(){
    webDriver.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    click(By.cssSelector("input[value='Войти']"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Войти']"));
  }

  public void logout(){
    webDriver.get(app.getProperty("web.baseUrl") + "/logout_page.php");
  }

}
