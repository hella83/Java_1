package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getContactHelper().gotoAddNew();
        app.getContactHelper().fillContactForm(new ContactData("FName1", "LName1", "TestAddress", "TestPhone", "TestMail"));
        app.getContactHelper().submitContactCreation();
        app.goToHomePage();
    }

}
