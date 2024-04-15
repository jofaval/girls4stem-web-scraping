package com.capgemini.catedrauv.girls4stem.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import com.capgemini.catedrauv.girls4stem.domain.UseCase.Girls4STEMUseCase;
import com.capgemini.catedrauv.girls4stem.domain.models.Expert;
import com.capgemini.catedrauv.girls4stem.infrastructure.CsvRepository;
import com.capgemini.catedrauv.girls4stem.infrastructure.database.ExpertDbRepository;

public class Girls4STEMService implements Girls4STEMUseCase {
    public final static int MAIN_PAGE_SLEEP_TIME = 5_000;

    private ExpertService expertService;

    public Girls4STEMService() {
        expertService = new ExpertService();
    }

    public static void visitExpertsPage() throws InterruptedException {
        System.out.println("Se inicia la aplicación");

        System.out.println("Visitando la página de las expertas");
        SeleniumService.getInstance().visit(System.getProperty("BASE_URL"));

        Thread.sleep(MAIN_PAGE_SLEEP_TIME);
    }

    @Override
    public List<Expert> findExperts() throws InterruptedException {
        visitExpertsPage();

        System.out.println("Buscando a las expertas");
        List<WebElement> speakers = SeleniumService.getInstance().findMany(".single-speaker-area");

        System.out.println();
        System.out.println("Número de expertas: " + speakers.size());
        System.out.println();

        return speakers.stream().map(expertService::toExpert).collect(Collectors.toList());
    }

    @Override
    public void saveToCsv(List<Expert> experts) {
        CsvRepository csvRepository = new CsvRepository();

        csvRepository.save(System.getProperty("CSV_FILENAME"), experts);
    }

    @Override
    public int saveToDb(List<Expert> experts) throws SQLException {
        ExpertDbRepository expertDbRepository = new ExpertDbRepository();

        List<String> rows = new ArrayList<String>();
        for (Expert expert : experts) {
            rows.add(expertDbRepository.toDbRow(expert));
        }

        return DatabaseService.getInstance().insertMany(System.getProperty("EXPERTS_TABLE"), (String[]) rows.toArray());
    }
}
