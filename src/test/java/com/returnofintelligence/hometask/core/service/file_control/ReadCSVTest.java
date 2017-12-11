package com.returnofintelligence.hometask.core.service.file_control;

import com.returnofintelligence.hometask.core.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans/readCSVBean.xml"})
public class ReadCSVTest {

    private Map<String, ArrayList<Person>> users;

    @Resource(name = "p1")
    private Person p1;

    @Resource(name = "p2")
    private Person p2;


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