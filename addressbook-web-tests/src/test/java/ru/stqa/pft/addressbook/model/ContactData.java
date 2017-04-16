package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private  String firstname;

  @Column(name = "lastname")
  private  String lastname;

  @Column(name = "address")
  @Type(type = "text")
  private  String address;

  @Column(name = "home")
  @Type(type = "text")
  private  String homephone;

  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobilephone;

  @Column(name = "work")
  @Type(type = "text")
  private  String workphone;

  @Column(name = "email")
  @Type(type = "text")
  private  String email;

  @Column(name = "email2")
  @Type(type = "text")
  private  String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private  String email3;

  @Transient
  private  String allphones;
  @Transient
  private  String allemails;
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public File getPhoto() {
     if (photo != null) {
      return new File(photo);
     } else {
      return null;
    }
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllemails() {
    return allemails;
  }

  public ContactData withAllemails(String allemails) {
    this.allemails = allemails;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllphones() {
    return allphones;
  }

  public ContactData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public ContactData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }


  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }
  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getEmail() {
    return email;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }
}
