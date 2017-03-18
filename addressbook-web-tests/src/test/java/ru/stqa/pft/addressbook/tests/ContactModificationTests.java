package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Elena on 22.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().home();
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("FName1").withLastName("LName1").withGroup("test1"));
    }

  }

  @Test
  public void testContactModification(){
    app.goTo().home();
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("FName1mod").withLastName("LName1")
            .withAddress("newTestAddress").withHomephone("newTestPhone").withEmail("newTestMail");
    app.contact().modify(contact);
    app.goTo().home();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
