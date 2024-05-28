package org.qaitfactor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Simple Broken Links Checker
 */
public class BrokenLinksChecker {

    private static final Logger logger = LogManager.getLogger(BrokenLinksChecker.class);

    public static void main(String[] args) {

        // set ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // Initialize the browser and pass options
        WebDriver driver = new ChromeDriver(options);

        // open webpage example
        driver.get("https://testpages.eviltester.com");

        // Find all links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        logger.info("Number of links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                checkLink(url);
            } else {
                logger.warn("Invalid link: " + url);
            }
        }
        // Close the browser
        driver.quit();
    }

    /**
     * Checks the given URL to see if it is valid or broken.
     *
     * @param url the URL to check
     */
    public static void checkLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            int responseCode = httpConn.getResponseCode();

            if (responseCode >= 400) {
                logger.error(url + " is broken. Response code: " + responseCode);
            } else {
                logger.info(url + " is valid. Response code: " + responseCode);
            }
        } catch (IOException e) {
            logger.error(url + " is broken. Exception: " + e.getMessage());
        }
    }
}
