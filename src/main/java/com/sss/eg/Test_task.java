package com.sss.eg;

import com.sss.eg.po.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Created by Танечка on 12.02.2018.
 */
public class Test_task {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void prepareEnv() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void task() {
        driver.get("http://ss.com");
        String searchCriteria = "Компьютер";

        new HeaderPage(driver).switchToRuLang();

        new MainPage(driver).gotoElectronicsSection();

        new HeaderPage(driver).gotoSearchCriteriaPage();
        SearchCriteriaPage scp = new SearchCriteriaPage(driver);
        scp.setSearchText(searchCriteria);
        scp.selectPeriod(7);
        scp.selectRubric(Rubric.SALE);
        scp.setPriceRange(149, 302);
        scp.submitSearch();

        //get advertisement ids from search results page
        SearchResultsPage srp = new SearchResultsPage(driver);
        srp.selectRubric("Продажа");
        srp.sortByPrice();

        //
        srp.gotoExtendedSearch();

        scp = new SearchCriteriaPage(driver);
        scp.setPriceRange(160, 300);
        scp.submitSearch();

        Set<String> advs = new SearchResultsPage(driver).getSetAdvIds();
        Object[] keys = advs.toArray();

        //random select from search results
        Map<String, Adv> selectedAds = new HashMap<>();
        String key;
        do {
            key = (String) keys[new Random().nextInt(keys.length)];
            selectedAds.put(key, srp.getAdvById(key));
        } while (selectedAds.size() < 3);

        //pick checkboxes
        for (String advId : selectedAds.keySet()) {
            srp.selectAdvById(advId);
        }

        srp.addToFavorites();
        srp.confirmAlert();
        new HeaderPage(driver).gotoFavorites();
        Map<String, Adv> favoritesAds = new HashMap<>();
        FavoritesPage fp = new FavoritesPage(driver);
        for (String advId : fp.getSetAdvIds()) {
            favoritesAds.put(advId, fp.getAdvById(advId));
        }
        //verify selected and favorites ads
        for (String adId : selectedAds.keySet()) {
            assertTrue(selectedAds.get(adId).equals(favoritesAds.get(adId)));
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
