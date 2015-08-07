package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/
public class HeaderObjects {

    public static WebElement signInDropDown(WebDriver driver){

        return driver.findElement(By.className("AccountNavigation"));
    }

    public static WebElement usernameInput(WebDriver driver){

        return driver.findElement(By.id("usernameInput"));
    }

    public static WebElement passwordInput(WebDriver driver){

        return driver.findElement(By.id("passwordInput"));
    }

    public static WebElement loginButton(WebDriver driver){

        return driver.findElement(By.className("login-button"));
    }

    public static WebElement usernameDropDown(WebDriver driver){

        return driver.findElement(By.xpath("//*[@id=\"AccountNavigation\"]/li/div/a"));
    }
}
