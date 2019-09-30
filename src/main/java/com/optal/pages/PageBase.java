package com.optal.pages;

import com.optal.commons.CommonConstants;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.awaitility.Awaitility.with;
import static org.junit.Assert.fail;

public class PageBase extends PageObject {


    private static final Logger logger = LoggerFactory.getLogger("PageBase");

    @FindBy(css="title")
    private WebElement pageTitle;

    private boolean makeVisible = false;

    protected void setVisible(boolean vis) {
        makeVisible = vis;
    }

    protected boolean getVisible() {
        return makeVisible;
    }



    public void pageIsReady(long slow) {
        /* TODO: make use of the java awaitility library rather than Thread sleep?
                e.g:
                 await()
                  .atMost(5, SECONDS)
                  .until(isPageLoaded());
         */
        final int limit = CommonConstants.WAITLOOPCOUNT;
        int count = 0;
        try {
            Boolean isLoaded = isPageLoaded();
            while (!isLoaded && (count < limit)) {
                isLoaded = isPageLoaded();
                Thread.sleep(slow);
                count++;
            }
            logger.info("Exit pageIsReady on loop " + count);
        } catch (InterruptedException e) {
            logger.error("Sleep exception while waiting for page to load");
            e.printStackTrace();
        }
    }

    protected Boolean isPageLoaded() {
        String jsQuery = "function pageLoaded() "
                + "{var loadingStatus=(document.readyState=='complete');"
                + "return loadingStatus;};"
                + "return pageLoaded()";

        final int limit = CommonConstants.WAITLOOPCOUNT;
        boolean isSuccess = false;
        int count = 0;
        while (!isSuccess && (count < limit)) {
            try {
                isSuccess = (Boolean) evaluateJavascript(jsQuery);
            } catch (Exception exp) {
                count++;
                logger.info("Page loading javascript failed " + count + ". Trying again");
            }
        }
        return isSuccess;
    }

    public void goToUrl(String url) {
        openUrl(url);
    }

    public String getHtmlPageTitle() {
        return getTitle().toString();
    }


    protected void jsListSelect(String selector, String setting) {
        List<WebElement> wel = getDriver().findElements(By.cssSelector(selector));
        for (WebElement e : wel) {
            if (e.getText().equalsIgnoreCase(setting)) {
                logger.info("Setting selector to " + setting);
                e.click();
                break;
            }
        }
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jse2 = (JavascriptExecutor) this.getDriver();
        jse2.executeScript("arguments[0].scrollIntoView()", element);
    }

    public String moveToSecondBrowserTab() {
        ArrayList<String> newTab = new ArrayList<String>(this.getDriver().getWindowHandles());

        String mainWindow = this.getDriver().getWindowHandle();

        newTab.remove(mainWindow);
        logger.info("size " + newTab.size());
        for (String i : newTab) {
            logger.info("handle is " + i);
            this.getDriver().switchTo().window(i);
        }
        return mainWindow;

    }

    // TODO: replace this method with 'waitForOrFailAfter5Seconds'
    public void waitawhile(long awhileInSeconds) {
        try {
            Thread.sleep(awhileInSeconds * 1000);
        } catch (InterruptedException e) {
            logger.info("SLEEP got interrupted " + e);
        }
    }

    public void moveToBrowserTab(String tab) {
        this.getDriver().switchTo().window(tab);
    }

    public boolean onThisUrl(String url) {
        String onthis = this.getDriver().getCurrentUrl();
        logger.info("Currently on url " + onthis);
        return onthis.contains(url);
    }

    protected void performClick(WebElementFacade element) {

        final int limit = CommonConstants.WAITLOOPCOUNT;
        boolean isSuccess = false;
        int count = 0;
        while (!isSuccess && (count < limit)) {
            isSuccess = executeJavaScript("arguments[0].click();", element);
            count++;
        }

        pageIsReady(CommonConstants.FAST);
    }

    protected void insertValue(String value, WebElementFacade element) {

        final int limit = CommonConstants.WAITLOOPCOUNT;
        boolean isSuccess = false;
        int count = 0;
        while (!isSuccess && (count < limit)) {
            isSuccess = executeJavaScript("arguments[0].setAttribute('value', '" + value + "')", element);
            count++;
        }
    }


    protected boolean executeJavaScript(String script, WebElementFacade element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(script, element);
            return true;
        } catch (Exception exp) {
            logger.info("Javascript execution failed. Retrying...");
            return false;
        }
    }

    protected boolean executeJavaScript(String script) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript(script);
            return true;
        } catch (Exception exp) {
            logger.info("Javascript execution failed. Retrying...");
            return false;
        }
    }

    protected WebElement executeJavaScript(String script, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            return  (WebElement) js.executeScript(script, element);
        } catch (Exception exp) {
            logger.info("Javascript execution failed. Retrying...");
            throw new NoSuchElementException();
        }
    }
    public String getTextFromElement(WebElementFacade element) {
        return element.waitUntilVisible().getText();
    }


    public String getCurrentPageUrl(PageBase page) {
        String currentUrl = page.getDriver().getCurrentUrl();
        return currentUrl;
    }


    //public String getPageTitle() { return pageTitle.waitUntilPresent().getText(); }

    /* CHECK: what does this method do? Is it supposed to return a collection of elementText, or just one item?
        The method name implies it should return a boolean primitive?
     */
    protected String elementListContains(List<WebElementFacade> elementList, boolean exactMatch, String toFind) {

        int size = elementList.size();
        logger.info("number of elements in list s " + size);
        for (WebElementFacade theElement : elementList){
            String label = theElement.waitUntilVisible().getText();
            logger.info("Element text is " + label);
            if (exactMatch) {
                if (label.contentEquals(toFind)) {
                    logger.info("item found - returning text found");
                    return label;
                }
            } else {
                if (label.contains(toFind)) {
                    logger.info("item found - returning text found");
                    return label;
                }
            }
        }
        return "SEARCH ITEM NOT FOUND";
    }

    protected void selectFromElementList(List<WebElementFacade> elementList, boolean exactMatch, String toFind) {
        selectFromElementList(elementList, exactMatch, toFind, false);
    }

    protected void selectFromElementList(List<WebElementFacade> elementList, boolean exactMatch, String toFind, boolean catchException) {
        int size = elementList.size();
        logger.info("number of elements in list is " + size);
        for (WebElementFacade theElement : elementList) {
            String label = theElement.waitUntilVisible().getText();
            logger.info("Element text is " + label);
            if (exactMatch) {
                if (label.contentEquals(toFind)) {
                    logger.info("item found and selected");
                    theElement.waitUntilClickable().click();
                    return;
                }
            } else {
                if (label.contains(toFind)) {
                    logger.info("item found and selected");
                    theElement.waitUntilClickable().click();
                    return;
                }
            }
        }
        if (catchException) {
            throw new NoSuchElementException("SEARCH ITEM NOT FOUND");
        } else {
            logger.info( "SEARCH ITEM NOT FOUND");
        }
    }

    protected void navigateToPageFromList(List<WebElementFacade> pages, String page) {
        logger.info("Number of pages in collection: {}", pages.size());

        AtomicInteger pageCounter = new AtomicInteger(0);
        pages.forEach(pageText -> logger.info("Page Collection: {} - {}", pageCounter.incrementAndGet(), pageText.getText()));

        pages.stream()
                .filter(pageName -> pageName.getText().equals(page))
                .findAny().orElseThrow(NoSuchElementException::new)
                .waitUntilClickable().click();

        waitForOrFailWithProgress(CommonConstants.SFAST, CommonConstants.VSLOW,
                "Waiting for page: [" + page + "] to load...",
                () -> getTitle().toLowerCase().contains(page.toLowerCase()),
                "Page: [" + page + "] failed to load");
    }

    protected String getAllTextInList(List<WebElementFacade> elementList, String seperator) {
        int size = elementList.size();
        String outcome = "";
        logger.info("number of elements in list is " + size);
        for (WebElementFacade theElement : elementList){
            String label = theElement.waitUntilVisible().getText();
            logger.info("This Element text is " + label);
            outcome = outcome + seperator + label;

        }
        logger.info("returning " + outcome);
        return outcome;
    }

    protected void enableCheckbox(WebElementFacade elementFacade){
        String script = "arguments[0].setAttribute('class','checked')";
        executeJavaScript(script,elementFacade);
    }

    protected void disableCheckbox(WebElementFacade elementFacade){
        String script = "arguments[0].setAttribute('class','')";
        executeJavaScript(script,elementFacade);
    }

    /**
     * Utility method to wait for a condition, failing with the given message if the condition is not satisfied within a
     * standard period.
     *
     * @param pollInterval       specifies polling interval
     * @param duration           specifies total duration to wait
     * @param waitingMessage     message to display for each poll interval
     * @param conditionEvaluator condition evaluator, called until true or timeout
     * @param errorMessage       error message with which to fail on timeout
     */
    protected void waitForOrFailWithProgress(long pollInterval,
                                             long duration,
                                             String waitingMessage,
                                             Callable<Boolean> conditionEvaluator,
                                             String errorMessage) {
        try {
            with()
                    .pollInterval(pollInterval, TimeUnit.MILLISECONDS)
                    .await()
                    .atMost(duration, TimeUnit.MILLISECONDS)
                    .until(() -> {
                        logger.info(waitingMessage);
                        return conditionEvaluator.call();
                    });
        } catch (ConditionTimeoutException e) {
            fail(errorMessage + ": " + e);
        }
    }

    protected void waitForOrFail(long pollInterval,
                                 long duration,
                                 Callable<Boolean> conditionEvaluator,
                                 String errorMessage) {
        try {
            with()
                    .pollInterval(pollInterval, TimeUnit.MILLISECONDS)
                    .await()
                    .atMost(duration, TimeUnit.MILLISECONDS)
                    .until(conditionEvaluator);
        } catch (ConditionTimeoutException e) {
            fail(errorMessage + ": " + e);
        }
    }

    protected void waitForOrFailAfter5Seconds(Callable<Boolean> conditionEvaluator, String errorMessage) {
        waitForOrFail(CommonConstants.FAST, CommonConstants.SLOW, conditionEvaluator, errorMessage);
    }
}
