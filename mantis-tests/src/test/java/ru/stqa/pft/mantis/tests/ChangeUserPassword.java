package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangeUserPassword extends TestBase {

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    String user = app.getProperty("web.user");
    String email = app.getProperty("web.email");
    String password = "root";
    app.uiSession().loginFromUI();
    app.goTo().manageUsersPage();
    app.goTo().UserPage();
    app.goTo().resetPassword();
    app.uiSession().logout();
    List<MailMessage> mailMessages = app.james().waitForMail("root", "root", 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
