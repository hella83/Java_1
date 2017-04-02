package ru.stqa.pft.mantis.model;

/**
 * Created by Elena on 02.04.2017.
 */
public class Users {

  public int id;
  public  String name;
  public  String email;

  public Users(int id, String name, String email){
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Users() {
    this.id = 0;
    this.name = null;
    this.email = null;
  }


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

}
