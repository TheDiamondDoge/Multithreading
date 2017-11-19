package com.returnofintelligence.hometask.concurrency;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * Created by The Diamond Doge on 18.11.2017.
 * Tracking additions of new files to "in" folder
 */
public class WatchServiceForInput {

    private final Map<WatchKey, Path> keys;

    private WatchService watcher;
    private BlockingQueue<Path> queue;


    public WatchServiceForInput(Path dir, BlockingQueue<Path> queue) {
        watcher = null;
        try {
            this.watcher = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.keys = new HashMap<>();
        this.queue = queue;

        walkAndRegisterDirectories(dir);
    }

    // register enter/create cases
    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE
                // ,ENTRY_DELETE, ENTRY_MODIFY
        );
        keys.put(key, dir);
    }

    private void walkAndRegisterDirectories(final Path start) {
        // register directory and sub-directories
        try {
            Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    registerDirectory(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processEvents() throws InterruptedException {
        for (; ; ) {

            // wait for key to be signalled
            WatchKey key;

            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);

            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {

                WatchEvent.Kind kind = event.kind();

                // Context for directory entry event is the file name of entry
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);

                // add filepath to queue
                //System.out.println(child.toString());
                queue.put(child);

                // if directory is created, and watching recursively, then register it and its sub-directories
                if (kind == ENTRY_CREATE) {

                    if (Files.isDirectory(child)) {
                        walkAndRegisterDirectories(child);
                    }


                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
}
