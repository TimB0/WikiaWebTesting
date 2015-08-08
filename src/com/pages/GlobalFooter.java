package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Artur on 8/8/2015.
 */
public class GlobalFooter {

    public static WebElement footerContainer(WebDriver driver){

        return driver.findElement(By.className("global-footer"));
    }
}
