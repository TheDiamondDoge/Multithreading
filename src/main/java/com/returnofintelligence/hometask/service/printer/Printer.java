package com.returnofintelligence.hometask.service.printer;

import com.returnofintelligence.hometask.model.Person;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by The Diamond Doge on 18.11.2017.
 */
public class Printer {

    private final String ENCODING = "UTF-8";

    private Map<String, ArrayList<Person>> persons;
    private String outFilepath;

    public Printer(Map<String, ArrayList<Person>> persons, String outFilepath) {
        this.persons = persons;
        this.outFilepath = outFilepath;
    }

    public void csvPrinter() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter printWriter = new PrintWriter(outFilepath, ENCODING);

        Set<String> keys = persons.keySet();

        for (String key : keys) {
            ArrayList<Person> sessions = persons.get(key);
            printWriter.println(key);
            for (Person p : sessions) {
                printWriter.println(p);
            }
        }

        printWriter.close();
    }
}
