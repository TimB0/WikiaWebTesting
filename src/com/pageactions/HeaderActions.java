package com.pageactions;

import com.Runner;
import com.pageobjects.HeaderObjects;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/
public class HeaderActions {

    public static void login(WebDriver driver, String username, String password){

        // Waiting for 2 second or Webdriver thinks the next element is not attached to the DOM
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}

        // Hovering over the Sign In element to trigger the drop down
        Actions actions = new Actions(driver);
        actions.moveToElement(HeaderObjects.signInDropDown(driver)).build().perform();

        // Waiting for 2 second or Webdriver cannot find the next elements in the drop down
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}

        // Entering username into the edit box
        HeaderObjects.usernameInput(driver).sendKeys(username);

        // Entering password into the edit box
        HeaderObjects.passwordInput(driver).sendKeys(password);

        // Click the log in button to login with the above credentials
        HeaderObjects.loginButton(driver).click();

        // Next elements attribute does not update fast enough so waiting for 2 seconds
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}

        // Making sure that username is displayed in the user properties
        try{
            Assert.assertTrue(HeaderObjects.usernameDropDown(driver).getAttribute("title").toLowerCase().contains(username.toLowerCase()));
        }catch (AssertionError e){
            throw new AssertionError("User name is not part of the attribute after logging in!");
        }
    }

    public static void logout(WebDriver driver){

        // log out logic would go here but for the sake of time and the fact that code works as is this was not implemented
    }
}
