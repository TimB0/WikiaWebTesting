package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Artur Spirin on 8/7/15.
 **/

/**
 * You can keep on adding elements for the respective page to this class to expand the test coverage
 */

public class HomeworkPageObjects {

    public static WebElement contributeDropDown(WebDriver driver){

        return driver.findElement(By.xpath("//*[@id=\"WikiHeader\"]/div[1]/nav"));
    }

    public static WebElement contributeDropDown_AddVideo(WebDriver driver){

        return driver.findElement(By.linkText("Add a Video"));
    }
}
