package com.capgemini.catedrauv.girls4stem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.capgemini.catedrauv.girls4stem.models.Expert;

/**
 * Hello world!
 *
 */
public class App {
    public static ChromeDriver selenium;
    public final static String USER_AGENT = "";
    public final static String BASE_URL = "https://girls4stem.uv.es/#/expertaStem#expertsCards";

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

    /** Candidata a ser reemplazada */
    public static void visitExpertsPage() {
        getInstance().get(BASE_URL);

        try {
            Thread.sleep(MAIN_PAGE_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** Candidata a ser reemplazada */
    public static List<WebElement> findExperts() {
        return getInstance().findElements(By.className("single-speaker-area"));
    }

    /** Candidata a ser reemplazada */
    public static void openSpeaker(WebElement expert) {
        getInstance().executeScript("arguments[0].click();", expert);

        try {
            Thread.sleep(SPEAKER_SLEEP_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** Candidata a ser reemplazada */
    public static void closeSpeaker() {
        WebElement backdrop = getInstance().findElement(By.className("cdk-overlay-backdrop"));
        getInstance().executeScript("arguments[0].click();", backdrop);

        try {
            Thread.sleep(SPEAKER_SLEEP_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** Candidata a ser reemplazada */
    public static String findName() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("name")).getAttribute("value");
    }

    /** Candidata a ser reemplazada */
    public static String findFirstLastName() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("surname")).getAttribute("value");
    }

    /** Candidata a ser reemplazada */
    public static String findSecondLastName() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("surname2")).getAttribute("value");
    }

    /** Candidata a ser reemplazada */
    public static String findJob() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("job")).getAttribute("value");
    }

    /** Candidata a ser reemplazada */
    public static String findCompany() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("company")).getAttribute("value");
    }

    /** Candidata a ser reemplazada */
    public static String findJobField() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("field")).getAttribute("value");
    }

    /** Candidata a ser reemplazada */
    public static String findBiography() {
        WebElement detailsContainer = getInstance().findElement(By.className("mat-dialog-container"));
        return detailsContainer.findElement(By.id("bibliography")).getAttribute("value");
    }

    public static Expert getSpeakerInfo(WebElement expert) {
        openSpeaker(expert);
        Expert withDetails = new Expert();

        withDetails.name = findName();
        withDetails.firstLastName = findFirstLastName();
        withDetails.secondLastName = findSecondLastName();
        withDetails.job = findJob();
        withDetails.company = findCompany();
        withDetails.jobField = findJobField();
        withDetails.biography = findBiography();

        closeSpeaker();

        return withDetails;
    }

    public static Connection connect() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/", System.getenv("DB_USER"),
                System.getenv("DB_PASS"))) {
            return conn;
        }
    }

    /** Candidata a ser reemplazada */
    public static void saveExpert(Connection conn, Expert expert) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO experts (name, first_Last_Name, second_Last_Name, job, company, job_Field, biography) VALUES ("
                            +
                            expert.name + "," +
                            expert.firstLastName + "," +
                            expert.secondLastName + "," +
                            expert.job + "," +
                            expert.company + "," +
                            expert.jobField + "," +
                            expert.biography + ","
                            + ")");
        }
    }

    public static void save(List<Expert> expertsWithDetails) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/", System.getenv("DB_USER"),
                System.getenv("DB_PASS"))) {
            for (Expert expert : expertsWithDetails) {
                saveExpert(conn, expert);
            }
        }
    }

    public static void main(String[] args) {
        visitExpertsPage();

        List<WebElement> experts = findExperts();

        List<Expert> expertsWithDetails = new ArrayList<Expert>();

        System.err.println();
        System.err.println("Size");
        int index = 0;
        int size = LIMIT == 0 ? experts.size() : experts.size() - LIMIT;
        for (WebElement expert : experts) {
            if (LIMIT != 0 && index >= LIMIT) {
                break;
            }

            System.out.printf("%d/%d - ", index, size);
            System.out.println(String.valueOf(Math.floor((index / size) * 10000) / 100));
            expertsWithDetails.add(getSpeakerInfo(expert));
            index++;
        }

        getInstance().close();

        // try {
        // save(expertsWithDetails);
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }

        System.out.println("Hello world!!");
    }
}
