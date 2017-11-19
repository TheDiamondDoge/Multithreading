package com.returnofintelligence.hometask.service.file_control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by The Diamond Doge on 19.11.2017.
 *
 * Create new file in "out" directory and delete file from 'in' directory
 */
public class FileDelete {

    private String outDirectory;
    private Path inFilename;

    public FileDelete(Path path, String outDirectory){
        this.inFilename = path;
        this.outDirectory = outDirectory;
    }

    public void deleteFile() {
        try {
            File file = new File(inFilename.toString());

            if (file.delete()) {
                System.out.println(file.getName() + " was processed and deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
