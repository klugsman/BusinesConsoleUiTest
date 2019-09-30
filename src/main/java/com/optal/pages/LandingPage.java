package com.optal.pages;

import com.optal.waits.WebWaits;
import com.optal.webControls.ClickControl;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LandingPage extends PageBase {

    //@FindBy(id = "menuMyApps")
    //private WebElementFacade menuMyApps;



    @FindBy(css ="h2.bodyBackgroundHeaderMargin.floatLeft")
    private WebElement welcomeElement=null;

    @FindBy(id = "submenuMyApps")
    private WebElement submenuMyApps=null;



    public boolean validateLogin(String message) {
        WebWaits.waitForTextToBePresent(welcomeElement, message);
        return welcomeElement.getText().toLowerCase().contains(message.toLowerCase());

    }


    /*public RequestNewCardPage requestSingleCard() { //cardProfile
        ClickControl.click(menuMyApps);
        Actions action = new Actions(webDriver);
        action.moveToElement(submenuMyApps).moveToElement(webDriver.findElement(By.id("requestCards"))).click().build().perform();
        return PageFactory.initElements(webDriver, RequestNewCardPage.class);
    }*/
}
