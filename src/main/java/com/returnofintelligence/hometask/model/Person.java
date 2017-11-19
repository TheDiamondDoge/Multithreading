package com.returnofintelligence.hometask.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Created by The Diamond Doge on 17.11.2017.
 */
public class Person {

    private DateTime date;
    private String userId;
    private String URL;
    private int average;

    public Person(Person person) {
        copy(person);
    }

    public Person(int date, String userId, String URL, int average) {
        setDate(date);
        this.userId = userId;
        this.URL = URL;
        this.average = average;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(long date) {
        Date d = new Date(date * 1000);
        this.date = new DateTime(d).toDateTime(DateTimeZone.UTC);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public void plusSecondsToDate(int seconds) {
        date.plusSeconds(seconds);
    }

    public void copy(Person person) {
        this.date = person.getDate();
        this.userId = person.getUserId();
        this.URL = person.getURL();
        this.average = person.getAverage();
    }

    @Override
    public String toString() {
        return date.getMillis()/1000 + "," + userId + "," + URL + "," + average;
    }
}
