package com.optal.hooks;


import com.optal.browsers.WebDriverFactory;
import com.optal.waits.WebWaits;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.optal.utilities.EnvConfig;

import java.util.concurrent.TimeUnit;


public class ScenarioHooks extends EventFiringWebDriver {


    private static final WebDriver REAL_DRIVER =  WebDriverFactory.create();



    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };

    static {
        int ap = Runtime.getRuntime().availableProcessors();
        long tm = Runtime.getRuntime().totalMemory();
        long mm = Runtime.getRuntime().maxMemory();
        //Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public ScenarioHooks() {
        super(REAL_DRIVER);
    }

    @Override
    public void quit() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't quit this WebDriver. It's shared and will quit when the JVM exits.");
        }
        super.quit();
    }

    @Before(order = 2)
    public void deleteAllCookies() {
        System.out.println("IN  deleteAllCookies ====== ");
//        manage().window().maximize();
        manage().deleteAllCookies();
        String baseUrl = EnvConfig.getValue("base.url");
        System.out.println("BASEURL ====== " + baseUrl);
        navigate().to(baseUrl);
        WebWaits.checkPageReady();
        int time = Integer.parseInt(EnvConfig.getValue("default.timer"));
        manage().timeouts().implicitlyWait(time, TimeUnit.MICROSECONDS);
        WebWaits.waitForNoOfSeconds(1);
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if(!scenario.isFailed()) return;
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

}

