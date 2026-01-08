package com.example.entities;

public class Timestamp {
    private String timestamp;

    //Constructor
    public Timestamp(Timer timer){
        timestamp = (timer.toString());
    }

    //toString
    public String toString(){
        return timestamp;
    }
}
