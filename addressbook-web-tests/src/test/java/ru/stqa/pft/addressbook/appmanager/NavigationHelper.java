package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Elena on 22.02.2017.
 */
public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {

    super(wd);
  }

  public void gotoGroupPage() {

    click(By.linkText("groups"));
  }

  public void goToHomePage() {
    click(By.linkText("home"));
  }
}
