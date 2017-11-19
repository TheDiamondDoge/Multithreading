package com.returnofintelligence.hometask.service.file_control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by The Diamond Doge on 19.11.2017.
 */
public class FileDeleteCreate {

    private static String PREFIX = "\\avg_";
    private String outDirectory = "C:\\RoI\\out";
    private String inDirectory = "C:\\RoI\\in";
    private Path inFilename;

    public FileDeleteCreate(Path path){
        this.inFilename = path;
    }

    public String createFile() throws IOException {

        String resultPath = outDirectory + PREFIX + inFilename.getFileName();
        File f = new File(resultPath);

        f.getParentFile().mkdirs();
        f.createNewFile();

        return resultPath;
    }

    public void deleteFile() {
        try {
            File file = new File(inFilename.toString());

            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
