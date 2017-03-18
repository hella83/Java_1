package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("FName1mod").withLastName("LName1")
            .withAddress("newTestAddress").withHomephone("newTestPhone").withEmail("newTestMail");
    app.contact().modify(contact);
    app.goTo().home();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
