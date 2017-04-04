package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Elena on 03.04.2017.
 */
public class ContactInGroupsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions1(){
    if (app.db().contacts().size() == 0){
      app.goTo().home();
      app.contact().create(new ContactData().withFirstName("FName1").withLastName("LName1").withGroup("test1"));
    }
  }

  @BeforeMethod
  public void ensurePreconditions2(){
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1134561"));
    }
  }

  @Test
  public void testContactAddGroups(){
    Contacts beforeC = app.db().contacts();
    ContactData selectedContact = beforeC.iterator().next();
    Groups beforeG = app.db().groups();
    GroupData selectedGroup = beforeG.iterator().next();
    app.goTo().home();
    app.contact().addInGroup(selectedContact, selectedGroup);
    //Contacts after = app.db().contacts();

    //assertEquals(after.size(), before.size());

    //assertThat(after, equalTo(before.without(selectedContact).withAdded(contact)));
  }

  @Test
  public void testContactDeleteGroups(){
    Contacts beforeC = app.db().contacts();
    ContactData selectedContact = beforeC.iterator().next();
    Groups beforeG = app.db().groups();
    GroupData selectedGroup = beforeG.iterator().next();
    app.goTo().home();
    app.contact().addInGroup(selectedContact, selectedGroup);
    //Contacts after = app.db().contacts();

    //assertEquals(after.size(), before.size());

    //assertThat(after, equalTo(before.without(selectedContact).withAdded(contact)));
  }
}
