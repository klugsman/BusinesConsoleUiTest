package com.optal.webControls;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import com.optal.waits.WebWaits;

public class ClickControl extends BaseControl {

    private static Logger logger = LogManager.getLogger("TestLogger");


    public static void click(WebElement element){
        for(int counter = 0; counter <= time; counter++){
            try{
                if(isElementDisplayed(element) && isElementEnabled(element)){
                    element.click();
                    logger.info("Element is clicked");
                    return;
                }else{
                    WebWaits.waitForNoOfSeconds(time);
                    logger.info("Wait is applied "+counter);
                }
            }catch (Exception e){
                WebWaits.waitForNoOfSeconds(1);
            }
        }
    }

}
