package com.testssuites;

import com.Runner;
import com.pages.AddVideoPage;
import com.pages.GlobalHeader;
import com.pages.HomeworkPage;
import com.pages.VideosPage;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

@FixMethodOrder
public class Homework {

    private static WebDriver driver =  Runner.getDriver();

    /**
     * What ever you want to do with the test results can be passed in to the failed/succeeded methods respectively.
     * For example, if you want results to be reported to a DB or test managements system like TestLink,
     * you can pass that logic here
     */
    @Rule
    public TestRule listen = new TestWatcher() {

        @Override
        public void failed(Throwable t, Description description) {

            t.printStackTrace();
            System.out.println("[TestRule / failed] Test FAILED!\n");
        }

        @Override
        public void succeeded(Description description) {

            System.out.println("[TestRule / succeeded] Test PASSED!\n");

        }
    };

    @Test
    public void scenarioOne() throws InterruptedException {

        // Navigating to the URL: http://qm-homework.wikia.com
        driver.get("http://qm-homework.wikia.com");

        // Making sure that we were redirected to the right page
        Assert.assertTrue(driver.getCurrentUrl().contains("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia"));

        // Calling premade action to perform sign in function -- more details in the login method itself
        GlobalHeader.login(driver, Runner.username, Runner.password);
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
        Boolean loggedOut = GlobalHeader.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(loggedOut){
            GlobalHeader.login(driver, Runner.username, Runner.password);
        }

        // Clicking on the Contribute button to open the dropdown menu
        HomeworkPage.contributeDropDown(driver).click();

        // Clicking the Add Video menu button
        HomeworkPage.contributeDropDown_AddVideo(driver).click();
        Thread.sleep(1000); // Small pause to let the elements load properly

        // Making sure that we were taken to the right page
        Assert.assertTrue(driver.getCurrentUrl().contains("http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd"));

        // Entering url of the video file into the edit text field
        AddVideoPage.addUrlTextBox(driver).sendKeys("http://www.youtube.com/watch?v=h9tRIZyTXTI");

        // Clicking the Add button to submit the video
        AddVideoPage.addUrlButton(driver).click();
        Thread.sleep(1000); // Small pause to let the elements load properly

        // Creating variables for further assertions
        String msg = VideosPage.successfullyAddedMsg(driver).getText();
        String expectedMsg = "Video page File:The Best Classical Music In The World was successfully added.";
        String expectedLink = "File:The Best Classical Music In The World";

        // Making sure that we get the right flash msg after we added the video
        Assert.assertTrue(msg.contains(expectedMsg));

        // Clicking the link in the flash msg to go to the video
        VideosPage.successfullyAddedMsg_FileLink(driver, expectedLink).click();
        Thread.sleep(1000); // Small pause to let the elements load properly

        // Making sure that the URL is a match for the file name with exception for the underscores
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedLink.replaceAll(" ","_")));
    }
}
