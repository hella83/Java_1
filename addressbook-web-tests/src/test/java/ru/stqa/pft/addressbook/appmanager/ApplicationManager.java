package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by Elena on 22.02.2017.
 */
public class ApplicationManager {

  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser == org.openqa.selenium.remote.BrowserType.FIREFOX){
      wd = new FirefoxDriver();
    } else if (browser == org.openqa.selenium.remote.BrowserType.CHROME){
      wd = new ChromeDriver();
    } else if (browser == org.openqa.selenium.remote.BrowserType.IE){
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    sessionHelper = new SessionHelper(wd);
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
