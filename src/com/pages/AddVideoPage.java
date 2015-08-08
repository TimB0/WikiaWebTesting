package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Artur Spirin on 8/7/15.
 **/


/**
 * You can keep on adding elements for the respective page to this class to expand the test coverage
 */

public class AddVideoPage {

    public static WebElement addUrlTextBox(WebDriver driver){

        return driver.findElement(By.id("wpWikiaVideoAddUrl"));
    }

    public static WebElement addUrlButton(WebDriver driver){

        return driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/form/div/input"));
    }
}
