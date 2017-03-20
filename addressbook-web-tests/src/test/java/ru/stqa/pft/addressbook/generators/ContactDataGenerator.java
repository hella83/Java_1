package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.List;

/**
 * Created by Elena on 20.03.2017.
 */
public class ContactDataGenerator {

  public static void main(String[] args) {
    int count = Integer.parseInt(args[0]);
    File file  = new File(args[1]);

    List<ContactData> contacts = generateContact(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) {
  }

  private static List<ContactData> generateContact(int count) {
    return  null;
  }
}
