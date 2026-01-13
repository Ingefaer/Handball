package com.example.entities;

public class Timer {
    private int totalSeconds;


    //Default Constructor
    public Timer(){
        this.totalSeconds = 0;
    }

    //Constructor
    public Timer(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    //Get
    public int getTotalSeconds() {
        return totalSeconds;
    }

    //Get
    public int getSeconds() {
        return totalSeconds % 60;
    }

    //Get
    public int getMinutes() {
        return totalSeconds / 60;
    }

    //Set
    public void setTotalSeconds(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    //Metode
    public void count(){
        totalSeconds++;
    }

    private String normalize(int time){
        return (time < 10 ? "0" + time: "" + time);
    }

    @Override
    public String toString() {
        return normalize(getMinutes()) + ":" + normalize(getSeconds());
    }
}
