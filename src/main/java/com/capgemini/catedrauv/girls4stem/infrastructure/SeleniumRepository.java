package com.capgemini.catedrauv.girls4stem.infrastructure;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumRepository {
    private ChromeDriver driver;

    public SeleniumRepository() {
        driver = new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");

        String userAgent = System.getProperty("USER_AGENT");
        if (!userAgent.isEmpty()) {
            options.addArguments("user-agent=" + userAgent);
        }

        return options;
    }

    public WebElement find(String query) {
        return driver.findElement(By.cssSelector(query));
    }

    public void click(String query) {
        find(query).click();
    }

    public void click(WebElement element) {
        element.click();
    }

    public String getText(String query, WebElement container) {
        return container.findElement(By.cssSelector(query)).getAttribute("innerText").trim();
    }

    public String getText(String query) {
        return getText(query, find("body"));
    }

    public String getValue(String query, WebElement container) {
        return container.findElement(By.cssSelector(query)).getAttribute("value").trim();
    }

    public String getValue(String query) {
        return getValue(query, find("body"));
    }

    public void visit(String url) {
        driver.get(url);
    }

    public List<WebElement> findMany(String query) {
        return driver.findElements(By.cssSelector("single-speaker-area"));
    }
}
