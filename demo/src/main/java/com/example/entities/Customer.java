package com.example.entities;
// Customer er en DBO (DataBærende Objekt)

public class Customer {
    
  private int id;
  private String lastName, firstName, nickName;

// constructures
  public Customer(String lastName, String firstName, String nickName) {
    this(0, lastName, firstName, nickName);
  }

  public Customer(int id, String lastName, String firstName, String nickName) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.nickName = nickName;
  }

    /*
     * get metoder
     */
  public int getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getNickName() {
    return nickName;
  }

     /*
      * set metoder
      */
  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  @Override
  public String toString() {
    return "[customer: id=" + id +
             ", lastName=" + lastName +
            ", firstName=" + firstName +
           ", nickName=" + nickName + "]";
  }
    
}
