package com.capgemini.catedrauv.girls4stem.infrastructure;

import java.io.IOException;
import java.util.List;

import shared.ToCsv;

public class CsvRepository {
    public final String EXTENSION = ".csv";
    public final String SEPARATOR = ";";

    private FileRepository fileRepository;

    public CsvRepository() {
        fileRepository = new FileRepository();
    }

    public void save(String filename, String content) throws IOException {
        if (!filename.endsWith(EXTENSION)) {
            filename += EXTENSION;
        }

        System.out.println("Guardando en CSV...");

        content = System.getProperty("CSV_HEADERS") + FileRepository.JUMP_LINE + content;
        fileRepository.save(filename, content);

        System.out.println("¡¡CSV guardado!!");
    }

    public void save(String filename, List<ToCsv> elements) throws IOException {
        String content = "";

        for (ToCsv element : elements) {
            content += element.toCsv(SEPARATOR);
            content += SEPARATOR;
            content += FileRepository.JUMP_LINE;
        }

        save(filename, content);
    }

}
