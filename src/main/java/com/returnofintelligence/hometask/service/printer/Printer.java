package com.returnofintelligence.hometask.service.printer;

import com.returnofintelligence.hometask.model.Person;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by The Diamond Doge on 18.11.2017.
 *
 * Print data into new file in 'out' directory
 */
public class Printer {

    private final String PREFIX = "\\avg_";

    private Map<String, ArrayList<Person>> persons;
    private String outDirectory;
    private Path filePath;

    public Printer(Map<String, ArrayList<Person>> persons, String outDirectory, Path filePath) {
        this.persons = persons;
        this.outDirectory = outDirectory;
        this.filePath = filePath;
    }

    public void csvPrinter() throws IOException {
        File file = new File(outDirectory + PREFIX + filePath.getFileName());
        FileWriter writer = new FileWriter(file, true);

        Set<String> keys = persons.keySet();
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : keys) {
            ArrayList<Person> sessions = persons.get(key);
            stringBuilder.append(key + "\n");
            for (Person p : sessions) {
                stringBuilder.append(p.toString() + "\n");
            }
        }
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
