package com.example.entities;

public class Timestamp {

    //final fordi vi sætter timestamp en gang og aldrig ændrer den efterfølgende.
    private final int totalSeconds;

    //Constructor
    public Timestamp(Timer timer){
        totalSeconds = (timer.getTotalSeconds());
    }

    //toString
    public String toString(){
        return "totalsekunder i timestamp: " + totalSeconds;
    }
}
