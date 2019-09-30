package com.optal.pages;

import com.optal.waits.WebWaits;
import com.optal.webControls.ClickControl;
import com.optal.webControls.JavaScriptControl;
import com.optal.webControls.TextFieldsControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BasePage {

    private static final Logger logger = LoggerFactory.getLogger("BasePage");
    protected WebDriver webDriver;

    @FindBy(name="username")
    private WebElement usernameElement=null;
    @FindBy(name="password")
    private WebElement passwordElement=null;
    @FindBy(id ="btn_login")
    private WebElement loginButton = null;

    /*public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }*/



    public LandingPage loginWith(String username, String password) {
        TextFieldsControl.enterText(usernameElement, username);
        TextFieldsControl.enterText(passwordElement, password);
        ClickControl.click(loginButton);
//        Map<String, Object> RESULT = (Map<String, Object>) JavaScriptControl.runJsScript("return document.querySELECTOR().value = '23/01/2018';");
        return PageFactory.initElements(webDriver, LandingPage.class);
    }

    public boolean validateLogout(String message) {

        return webDriver.getPageSource().toLowerCase().contains(message.toLowerCase());
    }
}
