package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Elena on 03.04.2017.
 */
public class ContactInGroupsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().home();
      app.contact().create(new ContactData().withFirstName("FName1").withLastName("LName1"));
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1134561"));
    }
  }

  @Test
  public void testContactAddGroups() {
    Contacts contactsBefore = app.db().contacts();
    ContactData selectedContact = contactsBefore.iterator().next();
    Groups contactGroupsBefore = selectedContact.getGroups();
    Groups groupsBefore = app.db().groups();
    GroupData selectedGroup = groupsBefore.iterator().next();
    if (contactGroupsBefore.size() != 0) {
      if (groupsBefore.equals(contactGroupsBefore)) {
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test1134561335");
        app.group().create(group);
        selectedGroup = group.withId(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt());
        System.out.println(selectedGroup);
      } else {
        Groups diff = groupsBefore;
        for (GroupData group : contactGroupsBefore) {
            diff = diff.without(group);
        }
        selectedGroup = diff.iterator().next();
        }
      }
      app.goTo().home();
      app.contact().addInGroup(selectedContact, selectedGroup);
      Contacts contactsAfter = app.db().contacts();
      Groups contactGroupsAfter = null;
      for ( ContactData contact : contactsAfter ) {
      if (contact.equals(selectedContact)){
        contactGroupsAfter = contact.getGroups();
     }
   }
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(selectedGroup)));
    }

  @Test
  public void testContactDeleteGroups(){
    Contacts contactsBefore = app.db().contacts();
    ContactData selectedContact = contactsBefore.iterator().next();
    Groups contactGroupsBefore = selectedContact.getGroups();
    Groups groupsBefore = app.db().groups();
    GroupData selectedGroup = groupsBefore.iterator().next();
    if (contactGroupsBefore.size() == 0) {
      app.goTo().home();
      app.contact().addInGroup(selectedContact, selectedGroup);
      contactGroupsBefore = contactGroupsBefore.withAdded(selectedGroup);
    } else {
      selectedGroup = contactGroupsBefore.iterator().next();
    }
    app.goTo().home();
    app.contact().deleteFromGroup(selectedContact, selectedGroup);
    Contacts contactsAfter = app.db().contacts();
    Groups contactGroupsAfter = null;
    for ( ContactData contact : contactsAfter ) {
      if (contact.equals(selectedContact)){
        contactGroupsAfter = contact.getGroups();
      }
    }
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(selectedGroup)));
  }
}
