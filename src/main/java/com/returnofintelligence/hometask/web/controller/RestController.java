package com.returnofintelligence.hometask.web.controller;

import com.returnofintelligence.hometask.core.utils.GetProperties;
import com.returnofintelligence.hometask.core.utils.ProcessedFiles;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by The Diamond Doge on 09.12.2017.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("core/converter")
public class RestController {

    @RequestMapping("/processed")
    public ArrayList<String> getProcessed() {
        ArrayList<String> paths = ProcessedFiles.getProcessedFilesNames();

        if (paths.isEmpty()) {
            paths.add("Files: ");
        }

        return paths;
    }

    @RequestMapping("/inOutDirectory")
    public ArrayList<String> dirs(){
        GetProperties prop = new GetProperties();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(prop.getInDirectory());
        arrayList.add(prop.getOutDirectory());

        return arrayList;
    }

}
