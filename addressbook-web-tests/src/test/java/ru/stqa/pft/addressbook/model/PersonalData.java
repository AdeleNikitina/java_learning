package ru.stqa.pft.addressbook.model;

public class PersonalData {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private String group;

  public PersonalData(String firstname, String middlename, String lastname, String group){
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.group = group;
  }

  public String getFirstname() { return firstname; }

  public String getMiddlename() { return middlename; }

  public String getLastname() { return lastname; }

  public String getGroup() {
    return group;
  }

}
