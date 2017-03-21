package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 20.03.2017.
 */
public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file  = new File(args[1]);

    List<ContactData> contacts = generateContact(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
              ,contact.getAddress(),contact.getHomephone(), contact.getEmail(), contact.getPhoto()));
    }
    writer.close();
  }

  private static List<ContactData> generateContact(int count) {
    List<ContactData> contacts = new ArrayList<>();
    File photo = new File("src/test/resources/test.png");
    for (int i=0; i < count; i++){
      contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
              .withLastName(String.format("LastName %s", i)).withAddress(String.format("Address %s", i))
              .withHomephone(String.format("11-1%s", i)).withEmail(String.format("%s@mail.ru", i)).withPhoto(photo));
    }
    return  contacts;
  }
}
