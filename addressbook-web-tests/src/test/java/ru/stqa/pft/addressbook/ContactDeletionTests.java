package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        app.goToHomePage();
        app.selectElement();
        app.deleteSelectedContact();
        app.closeAlertWindow();
        app.goToHomePage();
    }

}
