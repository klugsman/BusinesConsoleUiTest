package com.optal.webControls;

import org.openqa.selenium.WebDriver;


public class MultiWindowsControl extends BaseControl{


    public static void switchToNewWindowPage(WebDriver driver){
        String currentHandle = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
            if(!winHandle.equalsIgnoreCase(currentHandle)){
                driver.switchTo().window(winHandle);
                return;
            }
        }
    }
}
