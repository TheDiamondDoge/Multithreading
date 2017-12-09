package com.returnofintelligence.hometask.core.service.file_control;

import com.returnofintelligence.hometask.core.model.Person;
import com.returnofintelligence.hometask.core.utils.WorkWithTime;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by The Diamond Doge on 17.11.2017.
 * <p>
 * Reading data from '.csv' file
 */
public class ReadCSV {

    private WorkWithTime wwf;
    private Map<DateTime, Person[]> persons;
    private ArrayList<Person> personArrayList;

    public Map readCSV(String path) {
        personArrayList = new ArrayList<>();
        persons = new HashMap<>();
        wwf = new WorkWithTime();

        synchronized (this) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    wwf.twoDaysSession(createObject(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wwf.getMapMadeFromFile();
    }


    public Person createObject(String line) {
        String[] attributes = line.split(",");

        int date = Integer.parseInt(attributes[0]);
        String userId = attributes[1];
        String link = attributes[2];
        int timeSpent = Integer.parseInt(attributes[3]);

        Person person = new Person(date, userId, link, timeSpent);

        return person;
    }

}


