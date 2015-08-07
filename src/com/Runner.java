package com;

import com.core.Utils;
import com.testssuites.ScenarioOne;
import com.testssuites.ScenarioTwo;
import org.junit.runner.JUnitCore;

/**
 * Created by Artur Spirin on 8/6/2015.
 **/

public class Runner {

    public static String driverID;
    public static String username = "ArturSp";
    public static String password = "scorpion90";

    public static void main(String[] args){

        Utils.createDriver(driverID);
        for(String arg:args){
            if(arg.contains("-b")){

            }else if(arg.contains("-u")){

            }else if(arg.contains("-p")){

            }
        }
        if(args[1] != null && args[2] != null){
            username = args[1];
            password = args[2];
        }

        JUnitCore.runClasses(ScenarioOne.class);
        JUnitCore.runClasses(ScenarioTwo.class);
    }
}
