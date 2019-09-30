package com.optal.webControls;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import com.optal.waits.WebWaits;


public class TextFieldsControl extends BaseControl{

    private static Logger logger = LogManager.getLogger("TestLogger");


    public static void enterText(WebElement element, String requiredText){
        for(int counter = 0; counter <= time; counter++){
            try {
                if(isElementDisplayed(element) && isElementEnabled(element)){
                    element.clear();
                    element.sendKeys(requiredText);
                    logger.info("Has entered "+requiredText);
                    return;
                }else{
                    WebWaits.waitForNoOfSeconds(2);
                }
                logger.trace("Unable to enter text "+requiredText);
                return;
            }catch (Exception e){
                WebWaits.waitForNoOfSeconds(1);
                return;
            }
        }
    }



}
