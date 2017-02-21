package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        gotoAddNew();
        fillContactForm(new ContactData("FName1", "LName1", "TestAddress", "TestPhone", "TestMail"));
        submitContactCreation();
        goToHomePage();
    }

}
