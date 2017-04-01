package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Elena on 01.04.2017.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration(){
    app.registration().start("user1", "1123");
  }
}
