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
    Contacts beforeContacts = app.db().contacts();
    ContactData selectedContact = beforeContacts.iterator().next();
    Groups contactInGroups = selectedContact.getGroups();
    Groups beforeGroups = app.db().groups();
    GroupData selectedGroup = beforeGroups.iterator().next();
    if (contactInGroups.size() != 0) {
      if (beforeGroups.equals(contactInGroups)) {
        app.goTo().groupPage();
        GroupData group = new GroupData().withName("test1134561335");
        app.group().create(group);
        selectedGroup = group.withId(app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt());
        System.out.println(selectedGroup);
      } else {
        Groups diff = beforeGroups;
        for (GroupData group : contactInGroups) {
            diff = diff.without(group);
        }
        selectedGroup = diff.iterator().next();
        }
      }
      app.goTo().home();
      app.contact().addInGroup(selectedContact, selectedGroup);
      app.goTo().home();
      Contacts afterContacts = app.db().contacts();
      Groups contactInGroupsAfter = null;
      for ( ContactData contact : afterContacts ) {
      if (contact.equals(selectedContact)){
        contactInGroupsAfter = contact.getGroups();
     }
   }
    System.out.println(contactInGroupsAfter);
    System.out.println(selectedGroup);
    System.out.println(contactInGroups);
    System.out.println(contactInGroups.withAdded(selectedGroup));
      assertThat(contactInGroupsAfter, equalTo(contactInGroups.withAdded(selectedGroup)));


    }

  @Test(enabled = false)
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
