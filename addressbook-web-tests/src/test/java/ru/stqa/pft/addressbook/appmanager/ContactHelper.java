package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 22.02.2017.
 */
public class ContactHelper extends BaseHelper{


  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoAddNew() {

    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initContactModification(int index) {
    click(By.xpath("//table[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img"));

    //click(By.xpath(".//img[@title='Edit']"));

    //click(By.tagName("Edit"));
  }

  public void submitContactmodification() {
    click(By.name("update"));
  }


  public void createContact(ContactData contact) {
    gotoAddNew();
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();

    List<WebElement> elements = wd.findElements(By.name("entry"));
     for (WebElement element : elements)
       {
         String fname = element.findElement(By.xpath(".//td[3]")).getText();
         String lname = element.findElement(By.xpath(".//td[2]")).getText();
         //String id = element.findElement(By.xpath(".//td[1]")).getText();
         String id = element.findElement(By.tagName("input")).getAttribute("id");
        ContactData contact = new ContactData(id, fname, lname, null, null, null, null);
        contacts.add(contact);
      }
    return contacts;
  }
}
