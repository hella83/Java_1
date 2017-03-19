package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().home();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("FName1").withLastName("LName1")
                .withAddress("Moscow").withEmail("111@111")
                .withHomephone("7").withMobilephone("65").withWorkphone("3");
        app.contact().create(contact);
        app.goTo().home();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

}
