package com.pages;

import com.Runner;
import com.core.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

/**
 * You can keep on adding elements for the respective page to this class to expand the test coverage
 */

public class GlobalHeader {

    // ---------------------- Page Elements --------------------- //

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

    public static WebElement searchInput(WebDriver driver){

        return driver.findElement(By.id("searchInput"));
    }

    public static WebElement searchSubmit(WebDriver driver){

        return driver.findElement(By.id("searchSubmit"));
    }

    public static WebElement startWikiaButton(WebDriver driver){

        return driver.findElement(By.className("start-wikia"));
    }

    public static WebElement wikiaLogo(WebDriver driver){

        return driver.findElement(By.className("wikia-logo"));
    }

    public static WebElement hubMenu(WebDriver driver){

        return driver.findElement(By.id("hubsEntryPoint"));
    }

    public static WebElement hubMenu_ComicsLink(WebDriver driver){

        return driver.findElement(By.className("comics.hub-link.active"));
    }

    public static WebElement hubMenu_TvLink(WebDriver driver){

        return driver.findElement(By.className("tv.hub-link"));
    }

    public static WebElement hubMenu_MoviesLink(WebDriver driver){

        return driver.findElement(By.className("movies.hub-link"));
    }

    public static WebElement hubMenu_BooksLink(WebDriver driver){

        return driver.findElement(By.className("books.hub-link"));
    }

    public static WebElement hubMenu_GamesLink(WebDriver driver){

        return driver.findElement(By.className("games.hub-link"));
    }

    public static WebElement hubMenu_LifestyleLink(WebDriver driver){

        return driver.findElement(By.className("lifestyle.hub-link"));
    }

    // ----------------------- Page Actions ---------------------------- //

    /**
     * This method will perform high level action of logging in with the given params
     * @param driver = Pass in the current WebDriver
     * @param username = Pass in the username you want to login with
     * @param password = Pass in the password for the username
     */
    public static void login(WebDriver driver, String username, String password){

        // Hovering over the Sign In element to trigger the drop down
        Actions actions = new Actions(driver);
        actions.moveToElement(signInDropDown(driver)).build().perform();

        // Entering username into the edit box
        usernameInput(driver).sendKeys(username);

        // Entering password into the edit box
        passwordInput(driver).sendKeys(password);

        // Click the log in button to login with the above credentials
        loginButton(driver).click();

        // Making sure that username is displayed in the user properties
        try{Thread.sleep(2000);}catch (InterruptedException e){e.printStackTrace();}
        String attribute = usernameDropDown(driver).getAttribute("title").toLowerCase();
        try{
            Assert.assertTrue(attribute.contains(username.toLowerCase()));
        }catch (AssertionError e){
            throw new AssertionError("User name is not part of the attribute after logging in! Got: " + attribute);
        }

    }

    public static void logout(WebDriver driver){

        // log out logic would go here but for the sake of time and the fact that code works as is this was not implemented
    }

    /**
     * This method will perform search for the given search string
     * @param driver = Pass in the current WebDriver
     * @param searchString = Pass in the string that you want to search for
     */
    public static void search(WebDriver driver, String searchString){

        searchInput(driver).clear();
        searchInput(driver).sendKeys(searchString);
        searchSubmit(driver).click();
        Utils.fluentWait(GlobalFooter.footerContainer(Runner.driver), Runner.driver);
    }

    public static void click(WebElement element){

        element.click();
        Utils.fluentWait(GlobalFooter.footerContainer(Runner.driver), Runner.driver);
    }
}
