package com.example.entities;
// Customer er en DBO (DataBærende Objekt)

public class Beverage {
    int id, brandID;
    String name;
    double deposit;

    /*
     * constructorer
     */
    public Beverage(int brandID, String name) {
        this(0, brandID, name, 0);
    }

     
    public Beverage(int brandID, String name, double deposit) {
        this(0, brandID, name, deposit);
    }


    public Beverage(int id, int brandID, String name, double deposit) {
        this.id = id;
        this.brandID = brandID;
        this.name = name;
        this.deposit = deposit;
    }

    /*
     * get metoder
     */
    public int getId() {
        return id;
    }

    public int getBrandID() {
        return brandID;
    }

    public String getName() {
        return name;
    }

    public Double getDeposit() {
        return deposit;
    }

     /*
      * set metoder
      */
    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(int brandID) {
        this.brandID = brandID;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }


      /*
      * to string 
      */

    public String toString() {
        return "[Beverage: id = " + id +
        ", brandID = " + brandID +
        ", name = " + name +
        ", deposit = " + deposit;
    }
}
