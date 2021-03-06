package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
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

  @Parameter(names = "-c", description = "GroupCount")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContact(count);
    if (format.equals("csv")) {
      saveCSV(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveXML(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveXML(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private void saveCSV(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)){
      for (ContactData contact : contacts){
        writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
                ,contact.getAddress(),contact.getHomephone(), contact.getEmail(), contact.getPhoto()));
      }
    }
  }

  private List<ContactData> generateContact(int count) {
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
