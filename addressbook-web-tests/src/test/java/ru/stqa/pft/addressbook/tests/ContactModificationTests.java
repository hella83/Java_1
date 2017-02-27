package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Elena on 22.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("FName1", "LName1", null, null, null, "test1"));
    }
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("FName1", "LName1", "newTestAddress", "newTestPhone", "newTestMail", null),false);
    app.getContactHelper().submitContactmodification();
    app.getNavigationHelper().goToHomePage();

  }
}
