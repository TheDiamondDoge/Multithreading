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
@ContextConfiguration(locations = {"/beans/customSortBean.xml"})
public class CustomSortTest {
    @Resource(name = "map")
    private Map<String, ArrayList<Person>> users;

    @Resource(name = "p1")
    private Person p1;

    @Resource(name = "p2")
    private Person p2;

    @Resource(name = "p3")
    private Person p3;

    @Resource(name = "key")
    private String key;


    @Test
    public void sortByUserId() throws Exception {
       new CustomSort().sortByUserId(users);

        ArrayList<Person> persons = users.get(key);

        Assert.assertEquals(persons.get(0).getUserId(), p3.getUserId());
        Assert.assertEquals(persons.get(1).getUserId(), p1.getUserId());
        Assert.assertEquals(persons.get(2).getUserId(), p2.getUserId());


    }

}