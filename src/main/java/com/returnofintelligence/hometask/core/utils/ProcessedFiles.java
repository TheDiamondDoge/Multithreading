package com.returnofintelligence.hometask.core.utils;

import java.util.ArrayList;

/**
 * Created by The Diamond Doge on 09.12.2017.
 */
public class ProcessedFiles {

    private static ArrayList<String> processedFilesNames = new ArrayList<>();

    public static ArrayList getProcessedFilesNames(){
        return processedFilesNames;
    }

    public static void add(String filename){
        processedFilesNames.add(filename);
    }
}
