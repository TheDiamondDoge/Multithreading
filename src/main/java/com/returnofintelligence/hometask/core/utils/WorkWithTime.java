package com.returnofintelligence.hometask.core.utils;

import com.returnofintelligence.hometask.core.model.Person;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;

/**
 * Created by The Diamond Doge on 17.11.2017.
 *
 * Functions to process session time
 */
public class WorkWithTime {

    private final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("dd-MMM-yyyy").withLocale(Locale.ENGLISH);
    private Map<String, ArrayList<Person>> people;

    public WorkWithTime() {
        people = new HashMap<>();
    }

    public Map getMapMadeFromFile() {
        return people;
    }

    public void twoDaysSession(Person person) {
        DateTime resultDate = person.getDate().toDateTime(DateTimeZone.UTC).plusSeconds(person.getAverage());

        if (resultDate.getDayOfWeek() != person.getDate().toDateTime(DateTimeZone.UTC).getDayOfWeek()) {

            int secondsOfTheNextDay = resultDate.getSecondOfDay();

            Person newPerson = new Person(person);
            newPerson.setDate(resultDate.getMillis() / 1000);
            newPerson.plusSecondsToDate(newPerson.getAverage());
            newPerson.setAverage(secondsOfTheNextDay);

            person.setAverage(person.getAverage() - secondsOfTheNextDay);
            addPersonToMap(newPerson);
            addPersonToMap(person);

        } else {
            addPersonToMap(person);
        }
    }

    public void addPersonToMap(Person person) {
        String keyDate = transformADateToString(person);

        if (people.containsKey(keyDate)) {
            ArrayList<Person> internalMapArrayList = people.get(keyDate);
            internalMapArrayList.add(person);
            people.put(keyDate, internalMapArrayList);
        } else {
            ArrayList<Person> internalMapArrayList = new ArrayList<>();

            internalMapArrayList.add(person);
            people.put(keyDate, internalMapArrayList);
        }
    }

    public String transformADateToString(Person person) {
        return DATE_FORMAT.print(person.getDate());
    }

    public void averageTime(Map<String, ArrayList<Person>> people) {
        Set<String> keys = people.keySet();
        for (String s : keys) {
            ArrayList<Person> persons = people.get(s);
            for (int i = 1; i < persons.size(); i++) {
                if ((persons.get(i).getUserId().equals(persons.get(i - 1).getUserId()))
                        && (persons.get(i).getURL().equals(persons.get(i - 1).getURL()))) {
                    persons.get(i).setAverage((persons.get(i).getAverage() + persons.get(i - 1).getAverage()) / 2);
                    persons.remove(i - 1);
                    i -= 1;
                }
            }
        }
    }
}
