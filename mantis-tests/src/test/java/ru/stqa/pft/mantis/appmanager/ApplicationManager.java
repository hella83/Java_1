package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elena on 22.02.2017.
 */
public class ApplicationManager {

  private final Properties properties;
  private String browser;
  private WebDriver wd;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;

  public ApplicationManager(String browser)  {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
  }

  public void stop() {
    if (wd !=null){
    wd.quit();
    }
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public HttpSession newSession(){
    return new HttpSession(this);
  }

  public String getProperty(String s) {
    return properties.getProperty(s);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public WebDriver getDriver() {
    if (wd ==null){
      if (Objects.equals(browser, org.openqa.selenium.remote.BrowserType.FIREFOX)){
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, org.openqa.selenium.remote.BrowserType.CHROME)){
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, org.openqa.selenium.remote.BrowserType.IE)){
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseURL"));
    }
    return wd;
  }
}
