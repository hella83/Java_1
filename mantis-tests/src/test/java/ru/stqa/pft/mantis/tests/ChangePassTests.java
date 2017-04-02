package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Elena on 02.04.2017.
 */
public class ChangePassTests extends TestBase{


  @Test
  public void testChangePass() throws IOException, MessagingException {
    app.changePass().loginAdmin("administrator", "root");
    int id = 0;
    String user= null;
    String email = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT id, username, email FROM mantis_user_table where username not like 'administrator'");
      rs.next();
      id = rs.getInt("id");
      user = rs.getString("username");
      email = rs.getString("email");
      rs.close();
      st.close();
      conn.close();
      System.out.println(id + "user"+"email");

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    long now = System.currentTimeMillis();
    app.mail().start();
    app.changePass().start(id);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String password = "password";
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));

    app.mail().stop();
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}
