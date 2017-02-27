package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("FName1", "LName1", null, null, null, "test1"));
        }
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.closeAlertWindow();
        app.getNavigationHelper().goToHomePage();
    }

}
