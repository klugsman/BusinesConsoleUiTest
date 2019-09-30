package com.optal.webControls;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class FrameControl extends BaseControl{

    private static Logger logger = LogManager.getLogger("TestLogger");

    public static void switchToFrameOrIframe(WebDriver driver){
        try {
            driver.switchTo().parentFrame();
            logger.info("Switch to frame or iframe on the web");
        }catch (Throwable t){
            logger.error("Error switching to frame/iframe"+t.getMessage());
        }
    }

    public static void switchBackToCurrentElement(WebDriver driver) {
        try {
            driver.switchTo().activeElement();
            logger.info("Switched back to current element on the web");
        }catch (Throwable t){
            logger.error("Error switching back to current web element "+t.getMessage());
        }
    }
}
