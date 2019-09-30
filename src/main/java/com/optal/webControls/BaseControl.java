package com.optal.webControls;

import com.optal.waits.WebWaits;

import org.openqa.selenium.WebElement;
import com.optal.utilities.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.logging.Logger;


public abstract class BaseControl {


    protected static int time = Integer.parseInt(EnvConfig.getValue("default.timer"));
    //private static Logger logger = LogManager.getLogger("TestLogger");
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseControl.class);



    protected static boolean isElementDisplayed(WebElement element){
        for(int i = 0; i < time; i++){
            if(element.isDisplayed()){
                logger.info("Element is displayed");
                return true;
            }else {
                WebWaits.waitForNoOfSeconds(1);
            }
        }
        return false;
    }

    protected static boolean isElementSelected(WebElement element){
        for(int i = 0; i < time; i++){
            if(element.isSelected()){
                boolean status = element.isSelected();
                logger.info("The element selected status is "+status);
                return true;
            }else {
                WebWaits.waitForNoOfSeconds(1);
            }
        }
        return false;
    }

    protected static boolean isElementEnabled(WebElement element){
        for(int i = 0; i < time; i++){
            if(element.isEnabled()){
                logger.info("The element enabled status is "+element.isEnabled());
                return true;
            }else {
                WebWaits.waitForNoOfSeconds(1);
            }
        }
        return false;
    }













}
