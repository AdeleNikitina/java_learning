package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPassword extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException, ServiceException {
    //skipIfNotFixed(0000001);
    Users users = app.db().users();
    UserData user = users.iterator().next();
    String email = user.getEmail();
    String password = "root";
    app.uiSession().loginFromUI();
    app.goTo().manageUsersPage();
    app.goTo().UserPage();
    app.goTo().resetPassword();
    app.uiSession().logout();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
    //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));
  }

  @AfterMethod (alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}
