package com.optal.webControls;


import com.optal.waits.WebWaits;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class TagControl extends BaseControl {

    private static Logger logger = LogManager.getLogger("TestLogger");


    public static void clickOnAnchorTag(WebElement element, String textOfAnchor){
        for(int counter = 0; counter <= time; counter++) {
            if (isElementDisplayed(element) && isElementEnabled(element)) {
                for (WebElement myElement : element.findElements(By.tagName("a"))) {
                    if (!myElement.getText().toLowerCase().contains(textOfAnchor.toLowerCase())) continue;
                    myElement.click();
                    logger.info("Element clicked");
                    return;
                }
            } else {
                WebWaits.waitForNoOfSeconds(2);;
                logger.warn("Waited as desired" + counter);
            }
        }
    }

    public static void clickOnSpanTag(WebElement element, By subLocator, String textOfAnchor){
        for(int counter = 0; counter <= time; counter++) {
            if (isElementDisplayed(element) && isElementEnabled(element)) {
                for(WebElement myElement : element.findElement(subLocator).findElements(By.tagName("span"))){
                    if(!myElement.getText().toLowerCase().contains(textOfAnchor.toLowerCase()))continue;
                    myElement.click();
                    return;
                }
            } else {
                WebWaits.waitForNoOfSeconds(2);;
                logger.warn("Waited as desired" + counter);
            }
        }
    }




    public static void clickOnLiTag(WebElement element, By subLocator, String textOfAnchor){
        for(int counter = 0; counter <= time; counter++) {
            if (isElementDisplayed(element) && isElementEnabled(element)) {
                for(WebElement myElement : element.findElement(subLocator).findElements(By.tagName("li"))){
                    if(!myElement.getText().toLowerCase().contains(textOfAnchor.toLowerCase()))continue;
                    myElement.click();
                    return;
                }
            } else {
                WebWaits.waitForNoOfSeconds(2);
                logger.warn("Waited as desired" + counter);
            }
        }
    }


    public static WebElement getElementFromMultiple(WebElement element, String tagType, String attributeName, String name){
        for(int counter = 0; counter <= time; counter++) {
            if (isElementDisplayed(element) && isElementEnabled(element)) {
                for(WebElement newElement : element.findElements(By.tagName(tagType))){
                    if(!newElement.getAttribute(attributeName).equalsIgnoreCase(name))continue;
                    return newElement;
                }
                return null ;
            } else {
                WebWaits.waitForNoOfSeconds(2);
                logger.warn("Waited as desired" + counter);
            }
        }
        return null;
    }


    public static WebElement getElementFromNestedWithClassName(WebElement element, String className, String tagType, String attributeName, String name){

        for(int counter = 0; counter <= time; counter++) {
            if (isElementDisplayed(element) && isElementEnabled(element)) {
                for(WebElement newElement : element.findElement(By.className(className)).findElements(By.tagName(tagType))){
                    if(!newElement.getAttribute(attributeName).equalsIgnoreCase(name))continue;
                    return newElement;
                }
                return null;
            } else {
                WebWaits.waitForNoOfSeconds(2);
                logger.warn("Waited as desired" + counter);
            }
        }
        return null;
    }

}
