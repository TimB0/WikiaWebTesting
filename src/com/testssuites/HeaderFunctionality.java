package com.testssuites;

import com.Runner;
import com.core.Utils;
import com.pages.CreateNewWikiPage;
import com.pages.GlobalFooter;
import com.pages.GlobalHeader;
import com.pages.WikiaMainPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artur Spirin on 8/8/2015.
 **/

public class HeaderFunctionality {

    public static WebDriver driver = Runner.getDriver();

    @BeforeClass
    public static void setUp(){

        driver.get(WikiaMainPage.URL);
    }

    @Test
    public void clickWikiaLogo(){

        String expectedUrl = WikiaMainPage.URL;
        GlobalHeader.click(GlobalHeader.wikiaLogo(driver));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.equals(expectedUrl));
    }

    @Test
    public void clickStartWikia(){

        String expectedUrl = CreateNewWikiPage.URL;
        GlobalHeader.click(GlobalHeader.startWikiaButton(driver));
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.equals(expectedUrl));
    }

    @Test
    public void search(){

        String searchTerm = "something interesting";
        GlobalHeader.search(driver, searchTerm);
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        Assert.assertTrue(actualUrl.contains(searchTerm.replaceAll(" ", "+")));
    }

    @Test
    public void navigateToComicsHub(){


    }

    @Test
    public void navigateToTvHub(){


    }

    @Test
    public void navigateToMoviesHub(){


    }

    @Test
    public void navigateToMusicHub(){


    }

    @Test
    public void navigateToBooksHub(){


    }

    @Test
    public void navigateToGamesHub(){


    }

    @Test
    public void navigateToLifestyleHub(){


    }
}
