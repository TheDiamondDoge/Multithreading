package com.returnofintelligence.hometask.core.utils;

import com.returnofintelligence.hometask.core.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
public class WorkWithTimeTest {

    private Map<String, ArrayList<Person>> users;
    private WorkWithTime wwt;
    private Person p1;
    private Person p2;
    private String key = "02-Jan-1970";

    @Before
    public void initialize(){
        users = new HashMap<>();
        wwt = new WorkWithTime();
        p1 = new Person(86390, "test", "http://www.test.ru", 20);
        p2 = new Person(86500, "test", "http://www.test.ru", 20);
    }

    @Test
    public void twoDaysSession() throws Exception {
        wwt.twoDaysSession(p1);
        users = wwt.getMapMadeFromFile();

        Assert.assertEquals(2, users.size());
    }

    @Test
    public void averageTime() throws Exception {
        wwt.twoDaysSession(p1);
        users = wwt.getMapMadeFromFile();

        ArrayList<Person> people = users.get(key);

        people.add(p2);
        users.put("02-Jan-1970", people);

        new CustomSort().sortByUserId(users);

        wwt.averageTime(users);
        people = users.get(key);

        Assert.assertEquals(15, people.get(0).getAverage());

    }

}