package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.Users;

import java.sql.*;

/**
 * Created by Elena on 02.04.2017.
 */
public class ChangePassHelper extends BaseHelper {

  public ChangePassHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String login, String password) {
    wd.get(app.getProperty("web.baseURL"));
    type(By.name("username"), login);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void start(int id) {
    wd.get(app.getProperty("web.baseURL") + "/manage_user_page.php");
    click(By.cssSelector("a[href=\"manage_user_edit_page.php?user_id="+id+"\"]"));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public Users dbConnection(){
    Users user = new Users();
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/bugtracker?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT id, username, email FROM mantis_user_table where username not like 'administrator'");
      rs.next();
      user = new Users(rs.getInt("id"),rs.getString("username"),rs.getString("email"));
      rs.close();
      st.close();
      conn.close();
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return user;
  }

  public void confirm(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
