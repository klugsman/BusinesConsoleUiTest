package com.optal.webdrivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebdriverManager implements DriverSource {

    private static final Logger logger = LoggerFactory.getLogger(WebdriverManager.class);

    private WebDriver driver;


    @Override
    public WebDriver newDriver() {

        logger.info("Instantiate webdriver ");

        // Set up webdriver based on -Ddriver input

        String inputDriver = System.getProperty("driver");

        if (inputDriver == null){
            inputDriver = "chrome";
        }

        switch (inputDriver){

            case ("chrome"):{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                WebDriverManager.chromedriver().version("76.0.3809.68");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                return driver;
            }
            case ("chrome-headless"):{
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1325x744");
                WebDriverManager.chromedriver().version("74.0.3729.6");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                return driver;
            }
            case ("firefox"):{
                WebDriverManager.firefoxdriver().setup();
                // switches unnecessary logging related to the gecko driver off
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case ("ie"):{
                InternetExplorerOptions options = new InternetExplorerOptions();
                options.setCapability("ignoreZoomSetting",true);
                options.setCapability("nativeEvents",false);
                options.setCapability("unexpectedAlertBehaviour","ignore");
                options.setCapability("ignoreProtectedModeSettings",true);
                options.setCapability("disable-popup-blocking", true);
                options.setCapability("enablePersistentHover", true);
                options.setCapability("requireWindowFocus",true);
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(options);
                driver.manage().window().maximize();
                return driver;
            }
            // There are currently no drivers that work with Edge atm..
            case ("edge"):{
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                return driver;
            }
        }
        return driver;
    }

    @Override
    public boolean takesScreenshots() {
        return false;
    }
}
