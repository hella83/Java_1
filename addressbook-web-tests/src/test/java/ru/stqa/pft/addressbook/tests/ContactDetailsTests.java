package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Elena on 19.03.2017.
 */
public class ContactDetailsTests extends TestBase{

  @Test
  public void testContactDetails(){
    app.goTo().home();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
    assertThat(cleaned(contactInfoFromDetailsForm), equalTo(mergeData(contactInfoFromEditForm)));
  }

    private String mergeData(ContactData contact) {
    return Arrays.asList(contact.getFirstname(), contact.getLastname(),contact.getAddress()
            ,contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining(""));

  }



  public static String cleaned (String details){
    return details.replaceAll("\\s","").replaceAll("[-():HMW]","");
  }
  }



