package org.fox.springdemo.rest;

public class EmployeeErrorResponse {

    private int id;
    private String message;
    private long timeStamp;

    public EmployeeErrorResponse() {
    }

    public EmployeeErrorResponse(int id, String message, long timeStamp) {
        this.id = id;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
