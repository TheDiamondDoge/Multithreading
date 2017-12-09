package com.returnofintelligence.hometask.core.service.file_control;

import com.returnofintelligence.hometask.core.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
public class ReadCSVTest {

    private Map<String, ArrayList<Person>> users;
    private Person p1;
    private Person p2;

    @Before
    public void initialize(){
        p1 = new Person(1455812018, "test", "http://ru.wikipedia.org", 100);
        p2 = new Person(1455812019, "user10", "http://hh.ru", 30);
    }

    @Test
    public void readCSV() throws Exception {
        users = new ReadCSV()
                .readCSV("src\\test\\resources\\csvFilesForTest\\4.csv");

        Set<String> key = users.keySet();
        Iterator<String> it = key.iterator();
        String keyFromSet = it.next();

        Assert.assertEquals("18-Feb-2016", keyFromSet);

        ArrayList<Person> personsFromUser = users.get(keyFromSet);

        Assert.assertEquals(p1, personsFromUser.get(0));
        Assert.assertEquals(p2, personsFromUser.get(1));

    }

}