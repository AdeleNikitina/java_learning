package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private String group;
  private final String address;
  private final String mobile;
  private final String email;


  public ContactData (int id, String firstname, String middlename,  String lastname, String group,
                      String address, String mobile, String email) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.group = group;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
  }

  public ContactData (String firstname, String middlename, String lastname, String group,
                      String address, String mobile, String email) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.group = group;
    this.address = address;
    this.mobile = mobile;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstname() { return firstname; }

  public String getMiddlename() { return middlename; }

  public String getLastname() { return lastname; }

  public String getGroup() {
    return group;
  }

  public String getAddress() { return address; }

  public String getMobile() { return mobile; }

  public String getEmail() { return email; }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }
}
