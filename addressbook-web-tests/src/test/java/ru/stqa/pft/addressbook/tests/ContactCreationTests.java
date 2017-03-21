package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().home();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/test.png");
        ContactData contact = new ContactData().withFirstName("FNamePhoto1").withLastName("LName1111")
                .withAddress("Moscow").withEmail("111@111")
                .withHomephone("7").withMobilephone("65").withWorkphone("3").withPhoto(photo);
        app.contact().create(contact);
        app.goTo().home();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

}
