package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Elena on 22.02.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().home();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withFirstName("FName1").withLastName("LName1").withGroup("test1"));
    }

  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size();
    ContactData contact = new ContactData()
            .withId(before.get(index-1).getId()).withFirstName("FName1mod").withLastName("LName1")
            .withAddress("newTestAddress").withHomephone("newTestPhone").withEmail("newTestMail");
    app.contact().modify(index, contact);
    app.goTo().home();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index-1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
