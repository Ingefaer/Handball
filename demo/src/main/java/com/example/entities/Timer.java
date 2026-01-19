package com.example.entities;

public class Timer {
    private int totalSeconds;

    //Default Constructor
    public Timer(){
        this.totalSeconds = 0;
    }

    //Get
    public int getTotalSeconds() {
        return totalSeconds;
    }
    private int getSeconds() {
        return totalSeconds % 60;
    }
    private int getMinutes() {
        return totalSeconds / 60;
    }

    //Metode
    public void count(){
        totalSeconds++;
    }

    private String normalize(int time){
        return (time < 10 ? "0" + time : "" + time);
    }

    @Override
    public String toString() {
        return normalize(getMinutes()) + ":" + normalize(getSeconds());
    }
}
