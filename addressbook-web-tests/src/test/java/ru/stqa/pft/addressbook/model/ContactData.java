package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String address;
  private final String mobile;
  private final String email;

  public ContactData (String address, String mobile, String email){
    this.address = address;
    this.mobile = mobile;
    this.email = email;
  }

  public String getAddress() { return address; }

  public String getMobile() { return mobile; }

  public String getEmail() { return email; }
}
