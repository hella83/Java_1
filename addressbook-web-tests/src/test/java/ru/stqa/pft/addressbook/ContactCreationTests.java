package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.gotoAddNew();
        app.fillContactForm(new ContactData("FName1", "LName1", "TestAddress", "TestPhone", "TestMail"));
        app.submitContactCreation();
        app.goToHomePage();
    }

}
