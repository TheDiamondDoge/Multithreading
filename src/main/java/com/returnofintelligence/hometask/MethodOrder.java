package com.returnofintelligence.hometask;

import com.returnofintelligence.hometask.model.Person;
import com.returnofintelligence.hometask.utils.CustomSort;
import com.returnofintelligence.hometask.utils.WorkWithTime;
import com.returnofintelligence.hometask.service.file_control.FileDeleteCreate;
import com.returnofintelligence.hometask.service.file_control.ReadCSV;
import com.returnofintelligence.hometask.service.printer.Printer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
public class MethodOrder{

    public void call(Path path){
        System.out.println("create objects");
        CustomSort customSort = new CustomSort();
        ReadCSV readCSV = new ReadCSV();
        WorkWithTime workWithTime = new WorkWithTime();
        FileDeleteCreate fileDeleteCreate = new FileDeleteCreate(path);

        System.out.println("create map");
        Map<String, ArrayList<Person>> people = readCSV.readCSV("C:\\1.csv");
        System.out.println("sort map");
        customSort.sortByUserId(people);
        System.out.println("average map");
        workWithTime.averageTime(people);

        System.out.println("File creation");

        try {
            Printer printer = new Printer(people, fileDeleteCreate.createFile());
            printer.csvPrinter();
            fileDeleteCreate.deleteFile();
        } catch (IOException e){e.printStackTrace();}

        customSort = null;
        readCSV = null;
        workWithTime = null;
        fileDeleteCreate = null;
        //printer = null;
    }

}
