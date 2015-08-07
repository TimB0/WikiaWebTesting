package com.testssuites;

import com.Runner;
import com.core.Utils;
import com.pageactions.HeaderActions;
import com.pageobjects.HeaderObjects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

public class ScenarioOne {

    private static WebDriver driver =  Utils.createDriver(Runner.driverID);

    @Before
    public void setUp(){

        // Navigating to the URL: http://qm-homework.wikia.com
        driver.get("http://qm-homework.wikia.com");

        // Checking if we are logged in or not. If we are, then we will log out
        Boolean loggedOut = HeaderObjects.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(!loggedOut){
            HeaderActions.logout(driver);
        }else if(loggedOut){
            System.out.println("logged out");
        }
        System.out.println("setup done");
    }

    @Test
    public void login() {

        // Navigating to the URL: http://qm-homework.wikia.com
        driver.get("http://qm-homework.wikia.com");

        // Making sure that we were redirected to the right page
        Assert.assertTrue(driver.getCurrentUrl().contains("http://qm-homework.wikia.com/wiki/QM_HomeWork_Wikia"));

        HeaderActions.login(driver, Runner.username, Runner.password);
    }
}
