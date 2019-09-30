package com.optal.webControls;


import com.optal.waits.WebWaits;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;



public class RadioControl extends BaseControl {


    private static Logger logger = LogManager.getLogger("TestLogger");

    public static void clickButton(WebElement element){
        for(int counter = 0; counter <= time; counter++){
            try {
                if(isElementEnabled(element)) {
                    element.click();
                    logger.info("Element is clicked");
                    return;
                }else{
                    WebWaits.waitForNoOfSeconds(2);
                }
            }catch (Exception e){
                WebWaits.waitForNoOfSeconds(1);
                return;
            }

        }
    }




}
