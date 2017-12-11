package com.returnofintelligence.hometask.core.utils;

import com.returnofintelligence.hometask.core.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"/beans/workWithTimeBean.xml"})
public class WorkWithTimeTest {

    private Map<String, ArrayList<Person>> users;

    @Resource(name = "wwt")
    private WorkWithTime wwt;

    @Resource(name = "p1")
    private Person p1;

    @Resource(name = "p2")
    private Person p2;

    @Resource(name = "key")
    private String key;

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