package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {

        goToHomePage();
        selectGroup();
        deleteSelectedContact();
        closeAlertWindow();
        goToHomePage();
    }

}
