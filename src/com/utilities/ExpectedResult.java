package com.utilities;

import com.Runner;
import com.pages.GlobalHeader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur on 8/9/2015.
 */
public class ExpectedResult {

    public static void url(String expectedUrl, WebDriver driver){

        String actualUrl = driver.getCurrentUrl();

        try{
            Assert.assertTrue(actualUrl.equals(expectedUrl));
            System.out.println("[ExpectedResult / url] Expected URL: " + expectedUrl);
            System.out.println("[ExpectedResult / url] Actual URL  : " + actualUrl);
        }catch (AssertionError error){
            throw new AssertionError("Expected URL: " + expectedUrl + " But got: " + actualUrl);
        }
    }

    public static void title(String expectedTitle, WebDriver driver){

        String actualTitle = driver.getTitle();

        try{
            Assert.assertTrue(actualTitle.equals(expectedTitle));
            System.out.println("[ExpectedResult / title] Expected Title: " + expectedTitle);
            System.out.println("[ExpectedResult / title] Actual Title  : " + actualTitle);
        }catch (AssertionError error){
            throw new AssertionError("Expected TITLE: " + expectedTitle + " But got: " + actualTitle);
        }
    }

    public static void loggedOut(WebDriver driver){

        // Making sure that we have logged out and username is not displayed in the user properties
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}
        String actualAttributeValue = GlobalHeader.usernameDropDown(driver).getAttribute("title");//.toLowerCase();
        try{
            String expectedAttributeValue = "log in";
            Assert.assertFalse(actualAttributeValue.contains(Runner.username));
            Assert.assertTrue(actualAttributeValue.equals(expectedAttributeValue));
            System.out.println("[ExpectedResult / loggedIn] Expected Attribute Text: " + expectedAttributeValue);
            System.out.println("[ExpectedResult / loggedIn] Actual Attribute Text  : " + actualAttributeValue);
        }catch (AssertionError e){
            throw new AssertionError("Did not log out! Got: " + actualAttributeValue);
        }
    }

    public static void loggedIn(String expectedUsername, WebDriver driver){

        // Making sure that we have logged in and username is displayed in the user properties
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}
        String attribute = GlobalHeader.usernameDropDown(driver).getAttribute("title");//.toLowerCase();
        try{
            Assert.assertTrue(attribute.contains(expectedUsername));
            System.out.println("[ExpectedResult / loggedIn] Expected Attribute Text: " + expectedUsername + " - My Page");
            System.out.println("[ExpectedResult / loggedIn] Actual Attribute Text  : " + attribute);
        }catch (AssertionError e){
            throw new AssertionError("User name is not part of the attribute text after logging in! Got: " + attribute);
        }
    }
}
