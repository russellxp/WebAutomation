package com.mercadolibre.workshop.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver driver;

    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        //options.addArguments("headless");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.mercadolibre.com.pe/");
    }

    public void exitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
