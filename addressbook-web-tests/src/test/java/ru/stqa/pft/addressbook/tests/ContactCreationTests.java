package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.gotoAddNew();
        app.fillContactForm(new ContactData("FName1", "LName1", "TestAddress", "TestPhone", "TestMail"));
        app.submitContactCreation();
        app.goToHomePage();
    }

}
