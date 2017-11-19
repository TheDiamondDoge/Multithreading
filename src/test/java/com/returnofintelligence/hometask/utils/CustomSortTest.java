package com.returnofintelligence.hometask.utils;

import com.returnofintelligence.hometask.model.Person;
import com.returnofintelligence.hometask.service.file_control.ReadCSV;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
public class CustomSortTest {

    private Map<String, ArrayList<Person>> users;
    private Person p1;
    private Person p2;
    private Person p3;
    private String key = "01-Jan-1970";

    @Before
    public void initialize(){
        p1 = new Person(1, "usertest3", "http://test.test",2);
        p2 = new Person(2,"usertest13", "http://test.test", 3);
        p3 = new Person(2,"usertest1", "http://test.test", 3);

        users = new HashMap<>();
        ArrayList<Person> persons = new ArrayList<>();

        persons.add(p1);
        persons.add(p2);
        persons.add(p3);

        String key = "01-Jan-1970";
        users.put(key, persons);

    }

    @Test
    public void sortByUserId() throws Exception {
       new CustomSort().sortByUserId(users);

        ArrayList<Person> persons = users.get(key);

        Assert.assertEquals(persons.get(0).getUserId(), p3.getUserId());
        Assert.assertEquals(persons.get(1).getUserId(), p1.getUserId());
        Assert.assertEquals(persons.get(2).getUserId(), p2.getUserId());


    }

}