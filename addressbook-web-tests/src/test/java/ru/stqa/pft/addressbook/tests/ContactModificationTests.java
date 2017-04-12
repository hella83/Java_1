package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Elena on 22.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().home();
      app.contact().create(new ContactData().withFirstName("FName1").withLastName("LName1"));
              //.withGroup("Test 1"));
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src/test/resources/test.png");
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("FName1mod").withLastName("LName1")
            .withAddress("newTestAddress").withHomephone("newTestPhone").withEmail("newTestMail").withPhoto(photo);
    app.goTo().home();
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
