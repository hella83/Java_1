package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        app.selectElement();
        app.deleteSelectedGroup();
        app.returnToGroupPage();
    }

}
