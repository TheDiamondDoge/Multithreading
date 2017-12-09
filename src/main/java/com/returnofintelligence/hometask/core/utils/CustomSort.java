package com.returnofintelligence.hometask.core.utils;

import com.returnofintelligence.hometask.core.model.Person;

import java.util.*;

/**
 * Created by The Diamond Doge on 18.11.2017.
 *
 * Sorting data by userID in ASC order
 */
public class CustomSort {

    public void sortByUserId(Map<String, ArrayList<Person>> persons) {
        Set<String> keys = persons.keySet();

        for (String s : keys) {
            ArrayList<Person> people = persons.get(s);
            Collections.sort(people, new Comparator<Person>() {
                public int compare(Person o1, Person o2) {
                    return extractInt(o1) - extractInt(o2);
                }

                int extractInt(Person person) {
                    String num = person.getUserId().replaceAll("\\D", "");
                    return num.isEmpty() ? 0 : Integer.parseInt(num);
                }
            });
        }
    }
}
