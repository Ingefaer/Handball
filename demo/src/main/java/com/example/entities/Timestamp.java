package com.example.entities;

public class Timestamp {

    //final fordi vi sætter timestamp en gang og aldrig ændrer den efterfølgende.
    private final String timestamp;

    //Constructor
    public Timestamp(Timer timer){
        timestamp = (timer.toString());
    }

    //toString
    public String toString(){
        return timestamp;
    }
}
