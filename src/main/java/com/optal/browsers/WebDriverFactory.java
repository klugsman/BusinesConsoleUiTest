package com.optal.browsers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.optal.utilities.EnvConfig;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();


    private WebDriverFactory() {

    }

    public static WebDriver getThreadedDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public static void setThreadedDriver(WebDriver webDriver) {
        DRIVER_THREAD_LOCAL.set(webDriver);
    }

    public static WebDriver create() {

        String browser = EnvConfig.getValue("browser");
        if (browser == null || browser.isEmpty()) {
            throw new IllegalStateException("The webdriver system property must be set");
        }
        try {
            createAndSetUpThreadedBrowser(browser);
            return getThreadedDriver();
        } catch (IllegalArgumentException e) {
            String msg = format("The webdriver system property '%s' did not match any " +
                            "existing browser or the browser was not supported on your operating system. " +
                            "Valid values are %s",
                    browser, stream(Drivers
                            .values())
                            .map(Enum::name)
                            .map(String::toLowerCase)
                            .collect(toList()));

            throw new IllegalStateException(msg, e);
        }
    }


    private static void createAndSetUpThreadedBrowser(String browser){
        WebDriver webDriver;
        if(System.getProperty("testbed").toLowerCase().contains("local")){
            webDriver = Drivers.valueOf(browser.toUpperCase()).newDriver();
        }else if(System.getProperty("testbed").toLowerCase().contains("stack")) {
            webDriver = BrowserStack.getRemoteDriver();
        }else{
            webDriver = RemoteCustomCaps.getRemoteDriver();
            System.out.println("##########################");
        }
        setThreadedDriver(webDriver);
    }





    private enum Drivers {
        FIREFOX {
            @Override
            public WebDriver newDriver() {
                return LocalCustomCaps.getFirefoxDriver();
            }
        }, CHROME {
            @Override
            public WebDriver newDriver() {
                return LocalCustomCaps.getChromeDriver();
            }
        }, OPERA {
            @Override
            public WebDriver newDriver() {
                DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
                return new OperaDriver();
            }
        }, PHANTOMJS {
            @Override
            public WebDriver newDriver() {
               return LocalCustomCaps.getPhantonJsDriver();
            }
        }, IE {
            @Override
            public WebDriver newDriver() {
               return LocalCustomCaps.getIeDriver();
            }
        }, EDGE {
            @Override
            public WebDriver newDriver() {
                return LocalCustomCaps.getEdgeDriver();
            }
        };

        public abstract org.openqa.selenium.WebDriver newDriver();
    }









    private static void waitFor(int seconds){
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
