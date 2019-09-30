package com.optal.webControls;


import com.optal.waits.WebWaits;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;


public class ButtonControl extends BaseControl{

    private static Logger logger = LogManager.getLogger("TestLogger");


    public static void clickButton(WebElement element){
        for(int counter = 0; counter <= time; counter++){
            try {
                if(isElementDisplayed(element) && isElementEnabled(element)){
                    element.click();
                    logger.info("Element is clicked");
                    return;
                }else{
                    WebWaits.waitForNoOfSeconds(2);
                    logger.info("Wait is applied "+counter);
                }
            }catch (Exception e){
                WebWaits.waitForNoOfSeconds(1);
            }
        }
    }


    public static String getButtonName(WebElement element){
        for(int counter = 0; counter <= time; counter++){
            if(isElementDisplayed(element) && isElementEnabled(element)){
                String text = element.getText();
                logger.info("Element text is "+text);
                return text;
            }else{
                WebWaits.waitForNoOfSeconds(2);
            }
        }
        return null;
    }



}
