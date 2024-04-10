package com.capgemini.catedrauv.girls4stem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.capgemini.catedrauv.girls4stem.models.Expert;

import es.uv.etse.gim.proh.model.AccesoBD;

/**
 * Aplicación que extrae información de la página web de Girls4STEM
 * 
 * @version 1.0
 * @author <a href="mailto:jose.fabra-valverde@capgemini.com">Pepe Fabra
 *         Valverde</a>
 *
 */
public class App {
    public static ChromeDriver selenium;

    public final static String USER_AGENT = "";
    public final static String BASE_URL = "https://girls4stem.uv.es/#/expertaStem#expertsCards";
    public final static String CSV_FILENAME = "experts.csv";

    public final static int MAIN_PAGE_SLEEP_TIME = 5_000;
    public final static int SPEAKER_SLEEP_TIME = 500;

    public final static int LIMITLESS = 0;
    public final static int LIMIT = LIMITLESS;

    public static ChromeDriver getInstance() {
        if (selenium == null) {
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
            if (!USER_AGENT.isEmpty()) {
                options.addArguments("user-agent=" + USER_AGENT);
            }

            selenium = new ChromeDriver(options);
        }

        return selenium;
    }

    public static void visitExpertsPage() {
        System.err.println("Se inicia la aplicación");

        System.err.println("Visitando la página de las expertas");
        getInstance().get(BASE_URL);

        try {
            Thread.sleep(MAIN_PAGE_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<WebElement> findExperts() {
        System.err.println("Buscando a las expertas");
        return getInstance().findElements(By.className("single-speaker-area"));
    }

    public static void waitForSpeakerActionClick(WebElement element) throws InterruptedException {
        getInstance().executeScript("arguments[0].click();", element);
        Thread.sleep(SPEAKER_SLEEP_TIME);
    }

    public static void openSpeaker(WebElement expert) throws InterruptedException {
        System.err.println("Abriendo el detalle de una experta");
        waitForSpeakerActionClick(expert);
    }

    public static void closeSpeaker() throws InterruptedException {
        System.err.println("Cerrando el detalle de una experta");
        waitForSpeakerActionClick(getInstance().findElement(By.className("cdk-overlay-backdrop")));
    }

    public static String notImplemented(String method) throws Error {
        throw new Error(method + " not implemented");
    }

    public static String findName() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("name")).getAttribute("value");
    }

    public static String findFirstLastName() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("surname")).getAttribute("value");
    }

    public static String findSecondLastName() {
        return "";
    }

    public static String findJob() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("job")).getAttribute("value");
    }

    public static String findCompany() {
        return "";
    }

    public static String findJobField() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("field")).getAttribute("value");
    }

    public static String findBiography() {
        return "";
    }

    public static Expert getSpeakerInfo(WebElement expert) throws InterruptedException {
        openSpeaker(expert);
        Expert withDetails = new Expert();

        withDetails.setName(findName().trim());
        withDetails.setFirstLastName(findFirstLastName().trim());
        withDetails.setSecondLastName(findSecondLastName().trim());
        withDetails.setJob(findJob().trim());
        withDetails.setCompany(findCompany().trim());
        withDetails.setJobField(findJobField().trim());
        withDetails.setBiography(findBiography().trim());

        System.err.println("Detalle de la experta: " + withDetails.getName());

        closeSpeaker();

        return withDetails;
    }

    public static List<Expert> getDetailsFromExperts(List<WebElement> experts)
            throws InterruptedException {
        List<Expert> expertsWithDetails = new ArrayList<Expert>();

        
        int size = LIMIT == 0 ? experts.size() : experts.size() - LIMIT;

        System.out.println();
        System.out.println("Número de expertas: " + experts.size());
        System.out.println();

        for (int index = 0; index < size; index++) {
            WebElement expert = experts.get(index);
            System.out.printf("Avance %3d/%3d - %.2f %%\n", index + 1, size, 100.0 * (index + 1) / size);
            expertsWithDetails.add(getSpeakerInfo(expert));
        }

        System.out.println("Lista de expertas: " + expertsWithDetails);

        return expertsWithDetails;
    }

    public static String toExpertCsvLine(Expert expert) {
        return String.join(
                ";",
                expert.getName(),
                expert.getFirstLastName(),
                expert.getSecondLastName(),
                expert.getJob(),
                expert.getJobField(),
                expert.getCompany(),
                expert.getBiography().replaceAll("\n", "\\\\n"));
    }

    public static String getCsvFromExperts(List<Expert> expertsWithDetails) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("name;first_last_name;second_last_name;job;job_field;company;biography");

        for (Expert expert : expertsWithDetails) {
            lines.add(toExpertCsvLine(expert));
        }

        return String.join("\n", lines);
    }

    public static void writeToFile(String path, String content) throws IOException {
        File file = new File(path);
        file.createNewFile();
        System.err.println("File generated at: " + file.getPath());

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file.getPath()), StandardCharsets.UTF_8));

        writer.write(content);
        writer.close();
    }

    public static void saveToCsv(List<Expert> expertsWithDetails) throws IOException {
        System.err.println("Guardando en CSV...");

        String content = getCsvFromExperts(expertsWithDetails);
        writeToFile("./" + CSV_FILENAME, content);

        System.err.println("¡¡CSV guardado!!");
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        visitExpertsPage();

        List<Expert> expertsWithDetails = getDetailsFromExperts(findExperts());
        
        saveToCsv(expertsWithDetails);

        getInstance().close();

        // To-Do: Insertar los datos en la base de datos a través de AccesoBD- Tarea de
        // los estudiantes
        AccesoBD.getInstance().insertExperts(expertsWithDetails);
    }
}
