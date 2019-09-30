package com.optal.support;


import com.optal.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.optal.browsers.WebDriverFactory;
import com.optal.pages.LandingPage;

public class WorldHelper {

    private WebDriver driver = WebDriverFactory.getThreadedDriver();
    private static BasePage basePage = null;
    private static LandingPage landingPage = null;


    public BasePage getBasePage(){
        if(basePage != null) return basePage;
        return PageFactory.initElements(driver, BasePage.class);
    }


    public LandingPage getLandingPage(){
        if(landingPage != null) return landingPage;
        return PageFactory.initElements(driver, LandingPage.class);
    }










}
