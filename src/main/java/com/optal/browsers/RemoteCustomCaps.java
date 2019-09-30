package com.optal.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteCustomCaps {
    private static String containerUrl = "https://selenium.services.nonprod.rscomp.systems/wd/hub";
    private static String filePath = File.separator;

    public static WebDriver getRemoteDriver() {
//        String chromeExtPath = System.getProperty("user.dir")+filePath+ "extResources" + filePath +"2.0.7_0.crx";
//        File addonpath = new File(chromeExtPath);
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
//        options.addExtensions(addonpath);
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--enable-javascript");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--fast-start");
        caps.setCapability(ChromeOptions.CAPABILITY, options);

        //Headless
        String headless = System.getProperty("headless");
        if (headless.contains("true")) {
            caps.setCapability("headless", true);
            caps.setCapability("disable-gpu", true);
        }
        URL url = null;
        try {
            url = new URL(containerUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, caps);
    }

}
