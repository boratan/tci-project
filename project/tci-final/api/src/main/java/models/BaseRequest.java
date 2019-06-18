package models;

import java.util.Date;

public class BaseRequest {
    private static int nextId = 1;
    private int id;
    private long timeInMilli;

    public BaseRequest() {
        this.id = nextId;
        nextId++;
        Date date = new Date();
        timeInMilli = date.getTime();
    }

    public long getTimeInMilli() {
        return timeInMilli;
    }

    public int getId() {
        return id;
    }
}
