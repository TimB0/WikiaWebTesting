package com.utilities;

import com.google.common.base.Function;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Artur Spirin on 8/7/2015.
 **/

public class Utils {

    public static WebDriver createDriver(String driverID){

        WebDriver driver;
        if(driverID.equals("Chrome")){
            driver = new ChromeDriver();
        }
        else{
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        return driver;
    }

    public static WebElement fluentWait(final WebElement webElement, WebDriver driver) {

        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });

        return  element;
    }

    public static void navigateTo(String URL, WebDriver driver){

        if(!driver.getCurrentUrl().equals(URL)){
            driver.get(URL);
            try{
                Assert.assertTrue(driver.getCurrentUrl().equals(URL));
                System.out.println("[Utils / navigateTo] Expected to get: " + URL);
                System.out.println("[Utils / navigateTo] Actually got   : " + driver.getCurrentUrl());
            }catch (AssertionError error){
                throw new AssertionError("Expected to get: " + URL + " but got: " + driver.getCurrentUrl());
            }
        }else{
            System.out.println("[Utils / navigateTo] Already on: " + driver.getCurrentUrl());
        }
    }
}
