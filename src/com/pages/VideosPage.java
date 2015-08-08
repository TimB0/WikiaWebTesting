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

public class VideosPage {

    public static WebElement successfullyAddedMsg(WebDriver driver){

        return driver.findElement(By.xpath("//*[@id=\"WikiaPage\"]/div[2]/div/div/div"));
    }

    public static WebElement successfullyAddedMsg_FileLink(WebDriver driver, String linkText){

        return driver.findElement(By.linkText(linkText));
    }
}
