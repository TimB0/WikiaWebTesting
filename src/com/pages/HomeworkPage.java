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

public class HomeworkPage {

    public static final String URL = "http://qm-homework.wikia.com";
    public static final String URL_REDIRECT = "http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia";
    public static final String TITLE = "";

    public static WebElement contributeDropDown(WebDriver driver){

        return driver.findElement(By.xpath("//*[@id=\"WikiHeader\"]/div[1]/nav"));
    }

    public static WebElement contributeDropDown_AddVideo(WebDriver driver){

        return driver.findElement(By.linkText("Add a Video"));
    }
}
