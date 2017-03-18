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
      app.contact().create(new ContactData("FName1", "LName1", null, null, null, "test1"));
    }

  }

  @Test
  public void testContactModification(){
    List<ContactData> before = app.contact().list();
    int index = before.size();
    ContactData contact = new ContactData(before.get(index-1).getId(),"FName1mod", "LName1", "newTestAddress", "newTestPhone", "newTestMail", null);
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
