package com.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Artur Spirin on 8/7/2015.
 */
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

}
