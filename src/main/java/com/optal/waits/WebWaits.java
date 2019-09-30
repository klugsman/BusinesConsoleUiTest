package com.optal.waits;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.optal.browsers.WebDriverFactory;
import com.optal.utilities.EnvConfig;
import com.optal.webControls.JavaScriptControl;

import java.util.List;
import java.util.Optional;



public class WebWaits {

    protected static int time = Integer.parseInt(EnvConfig.getValue("default.timer"));
    private static Logger logger = LogManager.getLogger("TestLogger");
    private static int maxTimeToWait = 15;
    private static WebDriver driver = WebDriverFactory.getThreadedDriver();


    public static Optional<WebElement> waitForElement(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, maxTimeToWait);
            logger.info("waiting for element to be visible ...");
            return wait.until(ExpectedConditions.visibilityOfAllElements(element)).stream().findFirst();
        }catch (Exception e){
            logger.error("Error occurred : "+ e.getMessage());
            return null;
        }
    }

    public static Boolean waitForTextToBePresent(WebElement element, String text){
        try {
            WebDriverWait wait = new WebDriverWait(driver, maxTimeToWait);
            logger.info("waiting for element to be visible ...");
            return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        }catch (Exception e){
            logger.error("Error occurred : "+ e.getMessage());
            return null;
        }
    }

    public static List<WebElement> waitForElements(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, maxTimeToWait);
            return wait.until(ExpectedConditions.visibilityOfAllElements(element));
        }catch (Exception e){
            return null;
        }
    }

    public static void waitForNoOfSeconds(int seconds){
        try {
            Thread.sleep(time * 15 * seconds);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    public static boolean checkPageReady(int numOfTimes) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForNoOfSeconds(1);
        //Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            return true;
        }

        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        //You can replace your value with 25 If you wants to Increase or decrease wait time.
        for (int i = 0; i < numOfTimes; i++) {
            waitForNoOfSeconds(3);
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                return true;
            }
        }
        return false;
    }


    public static boolean checkPageReady() {
        long navigationStart = (Long) JavaScriptControl.runJsScript("return window.performance.timing.navigationStart");
        long responseStart = (Long)JavaScriptControl.runJsScript("return window.performance.timing.responseStart");
        long domComplete = (Long)JavaScriptControl.runJsScript("return window.performance.timing.domComplete");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equalsIgnoreCase("complete")) {
            System.out.println("Page loaded at first attempt.");
            logger.info("Page loaded at first attempt");
            long backendPerformance = responseStart - navigationStart;
            long frontendPerformance = domComplete - responseStart;
            logger.info("frontendPerformance: " + frontendPerformance +" milliseconds");
            logger.info("backendPerformance: " + backendPerformance +" milliseconds");
            return true;
        }else {
            //This loop will rotate for 25 times to check If page Is ready after every 1 second.
            //You can replace your value with 25 If you wants to Increase or decrease wait time.
            for (int i = 0; i < 10; i++) {
                logger.info("Waiting for page to load correctly");
                waitForNoOfSeconds(3);
                //To check page ready state.
                if (js.executeScript("return document.readyState").toString().equals("complete")) {
                    logger.info("Page loaded correctly");
                    long backendPerformance = responseStart - navigationStart;
                    long frontendPerformance = domComplete - responseStart;
                    logger.info("frontendPerformance: " + frontendPerformance +" milliseconds");
                    logger.info("backendPerformance: " + backendPerformance +" milliseconds");
                    return true;
                }
                logger.warn("Unable to load page correctly at ateempt no : "+i);
            }
        }
        logger.error("Unable to wait for Page to load correctly before returning...");
        return false;
    }
}
