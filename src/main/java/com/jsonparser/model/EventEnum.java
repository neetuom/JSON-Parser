package com.jsonparser.model;



public enum EventEnum {
    ID("id"),
    STATE("state"),
    TYPE("type"),
    HOST("host"),
    TIMESTAMP("timestamp"),
    ALERT("alert");

    private final String event;

    //Constructor to initialize the instance variable
    EventEnum(String event) {
        this.event = event;
    }

    //Static method to display the Event name
    public String getEvent(){
        return this.event;
    }
}
