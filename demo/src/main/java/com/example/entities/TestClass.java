package com.example.entities;

public class TestClass {
    public static void main(String[] args) {
        Timer timer = new Timer(84);
        Timestamp timestamp = new Timestamp(timer);
        System.out.println(timestamp);
    }
}
