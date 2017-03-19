package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by Elena on 22.02.2017.
 */
public class ContactHelper extends BaseHelper{


  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id= '"+ id + "']")).click();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactmodification();
  }

  public void gotoAddNew() {

    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initContactModification(int id) {
    click(By.cssSelector("div a[href=\"edit.php?id=" + id + "\"]"));
  }

  public void submitContactmodification() {
    click(By.name("update"));
  }


  public void create(ContactData contact) {
    gotoAddNew();
    fillContactForm(contact, true);
    submitContactCreation();
  }

   public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements)
    {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String fname = element.findElement(By.xpath(".//td[3]")).getText();
      String lname = element.findElement(By.xpath(".//td[2]")).getText();
      String  allphones = element.findElement(By.xpath(".//td[6]")).getText();

      contacts.add(new ContactData().withId(id).withFirstName(fname).withLastName(lname)
      .withAllphones(allphones));
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModification(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
              .withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone);
  }

}
