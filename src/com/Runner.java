package com;

import com.core.Utils;
import com.testssuites.HeaderFunctionality;
import com.testssuites.Homework;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

public class Runner {

    public static WebDriver driver;
    public static String driverID;
    public static String username;
    public static String password;

    public static void main(String[] arg){

        // Try catch for running and debugging from idea and not via cmd line
        try{
            // Parsing args to set flags for which driver to create for testing
            if(arg[0].toLowerCase().contains("firefox")){
                driverID = "Firefox";
            }else{
                driverID = "Chrome";
            }
        }catch (ArrayIndexOutOfBoundsException e){
            driverID = "Chrome";
        }

        // Try catch for running and debugging from idea and not via cmd line as well as to allow username/password
        // ability for not passing in those args
        try {
            // parsing args to find out if we have username and password passed in as an arg. If not, we will use default
            if(arg[1].contains("-u:") && arg[2].contains("-p:")){
                username = arg[1].replaceAll("-u:", "");
                password = arg[2].replaceAll("-p:", "");
            }else{
                username = "defaultUsername";
                password = "defaultPassword";
            }
        }catch (ArrayIndexOutOfBoundsException e){
            username = "ArturSp";
            password = "scorpion90";
        }

        // Starting tests
        //Result results = JUnitCore.runClasses(HeaderFunctionality.class);
        Result results = JUnitCore.runClasses(Homework.class);
        System.out.println("Tests Ran: " + results.getRunCount());
        System.out.println("Tests Failed: " + results.getFailureCount());

        // Cleaning up
        driver.close();
        driver.quit();
    }

    public static WebDriver getDriver(){

        if(driver == null){
            driver = Utils.createDriver(driverID);
        }
        return driver;
    }
}
