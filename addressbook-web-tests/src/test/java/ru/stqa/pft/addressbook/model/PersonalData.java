package ru.stqa.pft.addressbook.model;

public class PersonalData {
  private final String firstname;
  private final String middlename;
  private final String lastname;

  public PersonalData(String firstname, String middlename, String lastname){
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
  }

  public String getFirstname() { return firstname; }

  public String getMiddlename() { return middlename; }

  public String getLastname() { return lastname; }
}
