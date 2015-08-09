package com.testssuites;

import com.Runner;
import com.pages.GlobalHeader;
import com.pages.WikiaMainPage;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
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

    /**
     * None of the tests have explicit assertions that is because URL and Title is being automatically asserted
     * behind the scenes. Any page specific unique elements can be added for assertions in the tests explicitly
     * as needed
     */
    @Test
    public void navigateToMainWikia(){

        GlobalHeader.click("wikia logo", driver);
    }

    @Test
    public void navigateToStartWikia(){

        GlobalHeader.click("start a wikia", driver);
    }

    @Test
    public void search(){

        GlobalHeader.search("something interesting hmmm", driver);
    }

    @Test
    public void navigateToComicsHub(){

        GlobalHeader.goToHub("comics", driver);
    }

    @Test
    public void navigateToTvHub(){

        GlobalHeader.goToHub("tv", driver);
    }

    @Test
    public void navigateToMoviesHub(){

        GlobalHeader.goToHub("movies", driver);
    }

    @Test
    public void navigateToMusicHub(){

        GlobalHeader.goToHub("music", driver);
    }

    @Test
    public void navigateToBooksHub(){

        GlobalHeader.goToHub("books", driver);
    }

    @Test
    public void navigateToGamesHub(){

        GlobalHeader.goToHub("games", driver);
    }

    @Test
    public void navigateToLifestyleHub(){

        GlobalHeader.goToHub("lifestyle", driver);
    }

    @Test
    public void login(){

        GlobalHeader.login(driver, Runner.username, Runner.password);
    }

    @Test
    public void logout(){

        GlobalHeader.logout(driver);
    }
}
