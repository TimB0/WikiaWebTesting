package com.pageactions;

import com.pageobjects.HeaderObjects;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

// In this class we would have all the actions that can be performed on the header of the page such as clicking the Wikia logo or opening the drop down
// For the interest of time I have put login method as one big method but idially it would need to be broken down into a number of smaller methods that
// would perform a very small part of the end to end process, like one method would move the mouse to the drop down and
// another could click the sign in button. Out of those small methods you can then build bigger action methods if needed

public class HeaderActions {

    public static void login(WebDriver driver, String username, String password){

        // Hovering over the Sign In element to trigger the drop down
        Actions actions = new Actions(driver);
        actions.moveToElement(HeaderObjects.signInDropDown(driver)).build().perform();

        // Entering username into the edit box
        HeaderObjects.usernameInput(driver).sendKeys(username);

        // Entering password into the edit box
        HeaderObjects.passwordInput(driver).sendKeys(password);

        // Click the log in button to login with the above credentials
        HeaderObjects.loginButton(driver).click();

        // Making sure that username is displayed in the user properties
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}
        String attribute = HeaderObjects.usernameDropDown(driver).getAttribute("title").toLowerCase();
        try{
            Assert.assertTrue(attribute.contains(username.toLowerCase()));
        }catch (AssertionError e){
            throw new AssertionError("User name is not part of the attribute after logging in! Got: " + attribute);
        }

    }

    public static void logout(WebDriver driver){

        // log out logic would go here but for the sake of time and the fact that code works as is this was not implemented
    }
}
