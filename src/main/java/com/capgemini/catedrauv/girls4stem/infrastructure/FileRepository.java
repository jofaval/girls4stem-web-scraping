package com.capgemini.catedrauv.girls4stem.infrastructure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileRepository {
    public final static String JUMP_LINE = System.lineSeparator();

    private File getFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();

        return file;
    }

    private BufferedWriter getWriter(File file) throws FileNotFoundException {
        return new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file.getPath()),
                        StandardCharsets.UTF_8));
    }

    public void save(String filename, String content) throws IOException {
        File file = getFile(filename);
        System.out.println("File located at: " + file.getPath());

        BufferedWriter writer = getWriter(file);

        writer.write(content);
        writer.close();
    }
}
