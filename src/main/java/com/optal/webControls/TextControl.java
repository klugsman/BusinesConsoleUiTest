package com.optal.webControls;


import com.optal.waits.WebWaits;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TextControl extends BaseControl {

    private static Logger logger = LogManager.getLogger("TestLogger");


    public static String getText(WebElement element){
        for(int counter = 0; counter <= time; counter++){
            if(isElementDisplayed(element) && isElementEnabled(element)){
                String text = element.getText();
                logger.info("Element text is "+text);
                return text;
            }else{
                WebWaits.waitForNoOfSeconds(2);
                logger.warn("Waited as desired" +counter);
            }
        }
        logger.trace("Unable to grab any text");
        return "";
    }

}
