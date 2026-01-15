package com.example.entities;

public class Timestamp {

    //final fordi vi sætter timestamp en gang og aldrig ændrer den efterfølgende.
    private final int totalSeconds;

    //Constructor
    public Timestamp(Timer timer){
        totalSeconds = (timer.getTotalSeconds());
    }

    public Timestamp(int totalSeconds){
        this.totalSeconds = totalSeconds;
    }

    public int getTotalSeconds(){
        return totalSeconds;
    }

        private String normalize(int time){
        return (time < 10 ? "0" + time: "" + time);
    }

    //toString
    public String toString(){
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return normalize(minutes) + ":" + normalize(seconds);
    }
}
