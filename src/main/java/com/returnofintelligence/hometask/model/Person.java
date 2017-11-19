package com.returnofintelligence.hometask.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Created by The Diamond Doge on 17.11.2017.
 *
 * Model of incoming data
 */
public class Person {

    private DateTime date;
    private String userId;
    private String URL;
    private int average;

    public Person(Person person) {
        this.date = person.getDate();
        this.userId = person.getUserId();
        this.URL = person.getURL();
        this.average = person.getAverage();
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

    public String getURL() {
        return URL;
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

    @Override
    public String toString() {
        return date.getMillis()/1000 + "," + userId + "," + URL + "," + average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (average != person.average) return false;
        if (!date.equals(person.date)) return false;
        if (!userId.equals(person.userId)) return false;
        return URL.equals(person.URL);
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + URL.hashCode();
        result = 31 * result + average;
        return result;
    }
}
