package com;

import com.core.Utils;
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

        //String[] arg = new String[3];
        //arg[0] = "-b:chrome";
        //arg[1] = "-u:ArturSp";
        //arg[2] = "-p:scorpion90";

        // Parsing args to set flags for which driver to create for testing
        if(arg[0].toLowerCase().contains("chrome")){
            driverID = "Chrome";
            //driver = Utils.createDriver(driverID);
        }else{
            driverID = "Firefox";
            //driver = Utils.createDriver(driverID);
        }

        // parsing args to find out if we have username and password passed in as an arg
        if(arg[1] != null && arg[2] != null){
            username = arg[1].replaceAll("-u:", "");
            password = arg[2].replaceAll("-p:", "");
        }else{
            username = "defaultUsername";
            password = "defaultPassword";
        }

        // Starting tests
        Result results = JUnitCore.runClasses(Homework.class);
        System.out.println("Suite 1 finished");
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
