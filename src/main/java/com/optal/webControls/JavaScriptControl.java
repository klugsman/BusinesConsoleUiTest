package com.optal.webControls;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.optal.browsers.WebDriverFactory;


public class JavaScriptControl extends BaseControl{
    private static Logger logger = LogManager.getLogger("TestLogger");
    private static WebDriver driver = WebDriverFactory.getThreadedDriver();

    public static String getJavaScriptAlertText() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        logger.info("Javascript text is "+text);
        alert.dismiss();
        return text;
    }

    public static void confirmJavaScriptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
        logger.info("Javascript alert accepted");
    }

    public static void dismissJavaScriptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        logger.info("Javascript alert dismissed");
    }

    public static Object runJsScript(String script){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return js.executeScript(script);
    }
}
