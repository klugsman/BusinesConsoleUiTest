package com.optal.webControls;

import com.optal.waits.WebWaits;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownControl extends BaseControl {

    private static Logger logger = LogManager.getLogger("TestLogger");


    public static void selectDropDownByVisibleText(WebElement element, String visibleText){
        for(int counter = 0; counter <= time; counter++){
            try {
                if(isElementDisplayed(element) && isElementEnabled(element)){
                    Select select = new Select(element);
                    select.selectByVisibleText(visibleText);
                    logger.info("Element is selected is "+ visibleText);
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

}
