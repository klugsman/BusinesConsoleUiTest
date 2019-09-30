package com.optal.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStack {

    private static final String USERNAME = System.getProperty("bsuser");//This is browserstack username
    private static final String AUTOMATE_KEY = System.getProperty("bskey"); //This is browserstack access key
    private static final String browserStackUrl = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    private static String filePath = File.separator;

    public static WebDriver getRemoteDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--enable-javascript");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--fast-start");
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        caps = browserStackDetails(caps);

        //Headless
        String headless = System.getProperty("headless");
        if (headless.contains("true")) {
            caps.setCapability("headless", true);
            caps.setCapability("disable-gpu", true);
        }


        java.net.URL url = null;
        try {
            url = new URL(browserStackUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, caps);
    }


    private static DesiredCapabilities browserStackDetails(DesiredCapabilities caps){
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "75.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        caps.setCapability("build", "version1");
        caps.setCapability("project", "BusinessConsole");
        return caps;
    }

}
