package com.returnofintelligence.hometask.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by The Diamond Doge on 19.11.2017.
 *
 * Read properties from "configuration.properties" file
 */
public class GetProperties {

    private final String CONFIG_FILE_NAME = "config.properties";

    private Properties properties;
    private String inDirectory;
    private String outDirectory;

    public GetProperties(){
        getProperties();
    }

    public String getInDirectory() {
        return inDirectory;
    }

    public String getOutDirectory() {
        return outDirectory;
    }

    public void getProperties(){
         properties = new Properties();

        try(InputStream input = new FileInputStream(CONFIG_FILE_NAME)) {
            properties.load(input);

            inDirectory = properties.getProperty("inDirectory");
            outDirectory = properties.getProperty("outDirectory");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
