package com.testssuites;

import com.Runner;
import com.pageactions.HeaderActions;
import com.pageobjects.AddVideoPageObjects;
import com.pageobjects.HeaderObjects;
import com.pageobjects.HomeworkPageObjects;
import com.pageobjects.VideosPageObjects;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

@FixMethodOrder
public class Homework {

    private static WebDriver driver =  Runner.getDriver();

    @Test
    public void scenarioOne() throws InterruptedException {


        // Navigating to the URL: http://qm-homework.wikia.com
        driver.get("http://qm-homework.wikia.com");

        // Making sure that we were redirected to the right page
        Assert.assertTrue(driver.getCurrentUrl().contains("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia"));

        // Checking if we are logged in or not. If we are, then we will log out
        Boolean loggedOut = HeaderObjects.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(!loggedOut){
            HeaderActions.logout(driver);
        }

        // Calling premade action to perform sign in function -- more details in the login method itself
        HeaderActions.login(driver, Runner.username, Runner.password);

    }

    @Test
    public void scenarioTwo() throws InterruptedException {


        // Navigating to the URL: http://qm-homework.wikia.com if not already on the page
        if(!driver.getCurrentUrl().equals("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia")){
            driver.get("http://qm-homework.wikia.com");

            // Making sure that we were redirected to the right page
            Assert.assertTrue(driver.getCurrentUrl().contains("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia"));
        }

        // Checking if we are logged in or not. If we are, then we will log out
        Thread.sleep(2000); // Small pause to let the elements load properly
        Boolean loggedOut = HeaderObjects.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(loggedOut){
            HeaderActions.login(driver, Runner.username, Runner.password);
        }

        // Clicking on the Contribute button to open the dropdown menu
        HomeworkPageObjects.contributeDropDown(driver).click();

        // Clicking the Add Video menu button
        HomeworkPageObjects.contributeDropDown_AddVideo(driver).click();
        Thread.sleep(1000); // Small pause to let the elements load properly

        // Making sure that we were taken to the right page
        Assert.assertTrue(driver.getCurrentUrl().contains("http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd"));


        // Entering url of the video file into the edit text field
        AddVideoPageObjects.addUrlTextBox(driver).sendKeys("http://www.youtube.com/watch?v=h9tRIZyTXTI");

        // Clicking the Add button to submit the video
        AddVideoPageObjects.addUrlButton(driver).click();
        Thread.sleep(1000); // Small pause to let the elements load properly

        // Creating variables for further assertions
        String msg = VideosPageObjects.successfullyAddedMsg(driver).getText();
        String expectedMsg = "Video page File:The Best Classical Music In The World was successfully added.";
        String expectedLink = "File:The Best Classical Music In The World";

        // Making sure that we get the right flash msg after we added the video
        Assert.assertTrue(msg.contains(expectedMsg));

        // Clicking the link in the flash msg to go to the video
        VideosPageObjects.successfullyAddedMsg_FileLink(driver, expectedLink).click();
        Thread.sleep(1000); // Small pause to let the elements load properly

        // Making sure that the URL is a match for the file name with exception for the underscores
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedLink.replaceAll(" ","_")));

    }
}
