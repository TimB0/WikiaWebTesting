package com.pages;

import com.Runner;
import com.utilities.ExpectedResult;
import com.utilities.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

/**
 * This class contains page elements that can be interacted with by WebDriver
 * It also contains higher level header actions for performing a small end to end actions such as logging in or
 * opening a page from the hub menu
 */
public class GlobalHeader {

    // ================================================================== //
    // ========================== Page Elements ========================= //

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

    public static WebElement logoutButton(WebDriver driver){

        return driver.findElement(By.linkText("Log out"));
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

        return driver.findElement(By.xpath("//*[@id=\"hubsEntryPoint\"]"));
    }

    public static WebElement hubMenu_ComicsLink(WebDriver driver){

        return driver.findElement(By.linkText("Comics"));
    }

    public static WebElement hubMenu_TvLink(WebDriver driver){

        return driver.findElement(By.linkText("TV"));
    }

    public static WebElement hubMenu_MoviesLink(WebDriver driver){

        return driver.findElement(By.linkText("Movies"));
    }

    public static WebElement hubMenu_MusicLink(WebDriver driver){

        return driver.findElement(By.linkText("Music"));
    }

    public static WebElement hubMenu_BooksLink(WebDriver driver){

        return driver.findElement(By.linkText("Books"));
    }

    public static WebElement hubMenu_GamesLink(WebDriver driver){

        return driver.findElement(By.linkText("Games"));
    }

    public static WebElement hubMenu_LifestyleLink(WebDriver driver){

        return driver.findElement(By.linkText("Lifestyle"));
    }

    // ======================== Page Elements END ======================= //
    // ================================================================== //


    // ================================================================== //
    // ======================== Page Actions ============================ //

    /**
     * This method will perform high level action of logging in with the given params
     * @param driver = Pass in the current WebDriver
     * @param username = Pass in the username you want to login with
     * @param password = Pass in the password for the username
     */
    public static void login(WebDriver driver, String username, String password){

        Boolean loggedOut = GlobalHeader.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(!loggedOut){
            System.out.println("[GlobalHeader / login] Already logged in. Need to log out before I can log in.");
            GlobalHeader.logout(driver);
        }

        // Hovering over the Sign In element to trigger the drop down
        System.out.println("[GlobalHeader / login] Logging in...");
        Actions actions = new Actions(driver);
        actions.moveToElement(signInDropDown(driver)).build().perform();
        // Entering username into the edit box
        usernameInput(driver).sendKeys(username);
        // Entering password into the edit box
        passwordInput(driver).sendKeys(password);
        // Click the log in button to login with the above credentials
        loginButton(driver).click();
        // Making the assertion to make sure that we have logged in - for details see the loggedIn method
        ExpectedResult.loggedIn(username, driver);
    }

    /**
     * This method will perform high level action of logging out with the given params
     * @param driver = Pass in the current WebDriver
     */
    public static void logout(WebDriver driver){

        Boolean loggedOut = GlobalHeader.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(loggedOut){
            System.out.println("[GlobalHeader / logout] Already logged out. Need to log in before I can logout.");
            GlobalHeader.login(driver, Runner.username, Runner.password);
        }

        // Hovering over the Sign In element to trigger the drop down
        System.out.println("[GlobalHeader / logout] Logging out...");
        Actions actions = new Actions(driver);
        actions.moveToElement(signInDropDown(driver)).build().perform();
        // Clicking the logout button
        logoutButton(driver).click();
        // Making the assertion to make sure that we have logged out - for details see the loggedIn method
        ExpectedResult.loggedOut(driver);
    }

    /**
     * This method will perform search for the given search string
     * @param driver = Pass in the current WebDriver
     * @param searchString = Pass in the string that you want to search for
     * It will perform assertion for the URL to contain the search string automatically so you do not have to write that
     * out in the tests - easier to maintain. This assertions can be expended or removed as needed without braking anything.
     */
    public static void search(String searchString, WebDriver driver){

        searchInput(driver).clear();
        searchInput(driver).sendKeys(searchString);
        searchSubmit(driver).click();
        Utils.fluentWait(GlobalFooter.footerContainer(driver), driver);
        String expectedStringInUrl = "Search?search=" + searchString.replaceAll(" ", "+");
        try{
            Assert.assertTrue(driver.getCurrentUrl().contains(expectedStringInUrl));
            System.out.println("[GlobalHeader / search] Expected: " + expectedStringInUrl + " in the url of the page");
            System.out.println("[GlobalHeader / search] Actual: " + driver.getCurrentUrl());
        }catch (AssertionError error){
            throw new AssertionError("URL: " + driver.getCurrentUrl() + " does not contain expected string: " + expectedStringInUrl);
        }
    }

    /**
     * This method will perform a lower level action of clicking on a visible element in the header
     * such as the Logo or any buttons that are not in any of the drop down menus
     * @param elementID = right now only supports "start a wikia" and "wikia logo" but this can be expanded as needed
     * @param driver = Pass in the current WebDriver
     */
    public static void click(String elementID, WebDriver driver){

        String expectedUrl;
        String expectedTitle;

        if(elementID == "start a wikia"){
            startWikiaButton(driver).click();
            expectedUrl = CreateNewWikiPage.URL;
            expectedTitle = CreateNewWikiPage.TITLE;
        }else if(elementID == "wikia logo"){
            wikiaLogo(driver).click();
            expectedUrl = WikiaMainPage.URL;
            expectedTitle = WikiaMainPage.TITLE;
        }else{
            throw new NullPointerException("Unrecognized element passed into the argument!");
        }

        Utils.fluentWait(GlobalFooter.footerContainer(driver), driver);
        ExpectedResult.url(expectedUrl, driver);
        ExpectedResult.title(expectedTitle, driver);
    }

    /**
     * This method will perform high level action of going to the page you specify from the hub menu
     * it will also automatically perform assertions for URL and Page Titles.
     * It will also perform assertions automatically for the URL and page titles
     * @param hubID = string of the hub menu item as you see it in the browser;
     * @param driver = Pass in the current WebDriver
     */
    public static void goToHub(String hubID, WebDriver driver){

        // Allows the hubID param to be case insensitive
        hubID = hubID.toLowerCase();

        // Opening the hub drop down menu
        Actions actions = new Actions(driver);
        actions.moveToElement(hubMenu(driver)).build().perform();

        // Creating logic for assertion
        String expectedUrl;
        String expectedTitle;
        if(hubID == "comics"){
            hubMenu_ComicsLink(driver).click();
            expectedUrl = ComicsHubPage.URL;
            expectedTitle = ComicsHubPage.TITLE;

        }else if(hubID == "tv"){
            hubMenu_TvLink(driver).click();
            expectedUrl = TvHubPage.URL;
            expectedTitle = TvHubPage.TITLE;

        }else if(hubID == "movies"){
            hubMenu_MoviesLink(driver).click();
            expectedUrl = MoviesHubPage.URL;
            expectedTitle = MoviesHubPage.TITLE;

        }else if(hubID == "music"){
            hubMenu_MusicLink(driver).click();
            expectedUrl = MusicHubPage.URL;
            expectedTitle = MusicHubPage.TITLE;

        }else if(hubID == "books"){
            hubMenu_BooksLink(driver).click();
            expectedUrl = BooksHubPage.URL;
            expectedTitle = BooksHubPage.TITLE;

        }else if(hubID == "games"){
            hubMenu_GamesLink(driver).click();
            expectedUrl = GamesHubPage.URL;
            expectedTitle = GamesHubPage.TITLE;

        }else if(hubID == "lifestyle"){
            try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
            hubMenu_LifestyleLink(driver).click();
            expectedUrl = LifestyleHubPage.URL;
            expectedTitle = LifestyleHubPage.TITLE;

        }else{
            throw new NullPointerException("Unrecognized element passed into the argument!");
        }

        Utils.fluentWait(GlobalFooter.footerContainer(driver), driver);
        ExpectedResult.url(expectedUrl, driver);
        ExpectedResult.title(expectedTitle, driver);
    }

    // ======================= Page Actions END ========================= //
    // ================================================================== //
}
