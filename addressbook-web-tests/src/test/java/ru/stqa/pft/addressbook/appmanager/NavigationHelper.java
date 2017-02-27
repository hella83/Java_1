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
  // if (isElementPresent(By.tagName("h1"))
         //  && wd.findElement(By.tagName("h1")).getText().equals("Groups")
    if (isElementPresent(By.xpath("//div[@id='content']//h1[.='Groups']"))
           && isElementPresent(By.name("new"))) {
     return;
    }
    click(By.linkText("groups"));
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
}
