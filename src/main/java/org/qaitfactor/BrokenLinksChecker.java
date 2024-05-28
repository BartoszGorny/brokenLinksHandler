package org.qaitfactor;

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
    public static void main(String[] args) {

        // set ChromeDriver options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // Initialize the browser and pass options
        WebDriver driver = new ChromeDriver(options);

        // open webpage
        driver.get("https://njrusmc.net/blog/blog.html");

        // Find all links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Number of links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                checkLink(url);
            } else {
                System.out.println("Invalid link: " + url);
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
                System.out.println(url + " is broken. Response code: " + responseCode);
            } else {
                System.out.println(url + " is valid. Response code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println(url + " is broken. Exception: " + e.getMessage());
        }
    }
}
