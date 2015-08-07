package com.testssuites;

import com.Runner;
import com.core.Utils;
import com.pageactions.HeaderActions;
import com.pageobjects.HeaderObjects;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur on 8/6/2015.
 **/

public class ScenarioTwo {

    WebDriver driver = Utils.createDriver(Runner.driverID);

    @Before
    public void setUp(){

        // Navigating to the URL: http://qm-homework.wikia.com
        driver.get("http://qm-homework.wikia.com");

        // Checking if we are logged in or not. If we are not, then we will login
        Boolean loggedOut = HeaderObjects.usernameDropDown(driver).getAttribute("title").toLowerCase().contains("log in");
        if(loggedOut){
            HeaderActions.login(driver, Runner.username, Runner.password);
        }
    }

    @Test
    public void addVideo(){


    }
}
