package com.returnofintelligence.hometask;

import com.returnofintelligence.hometask.model.Person;
import com.returnofintelligence.hometask.utils.CustomSort;
import com.returnofintelligence.hometask.utils.WorkWithTime;
import com.returnofintelligence.hometask.service.file_control.FileDelete;
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

    private String inDirectory;
    private String outDirectory;

    public MethodOrder(String outDirectory, String inDirectory){
        this.inDirectory = inDirectory;
        this.outDirectory = outDirectory;
    }

    public synchronized void call(Path path){

        CustomSort customSort = new CustomSort();
        ReadCSV readCSV = new ReadCSV();
        WorkWithTime workWithTime = new WorkWithTime();
        FileDelete fileDelete = new FileDelete(path, outDirectory);

        Map<String, ArrayList<Person>> people = readCSV.readCSV(path.toString());
        customSort.sortByUserId(people);
        workWithTime.averageTime(people);

        try {
            Printer printer = new Printer(people, outDirectory, path);

            printer.csvPrinter();
            fileDelete.deleteFile();
        } catch (IOException e){e.printStackTrace();}


        customSort = null;
        readCSV = null;
        workWithTime = null;
        fileDelete = null;
    }
}
