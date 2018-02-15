package com.sss.eg.po;

import com.sss.eg.Adv;
import com.sss.eg.Rubric;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;

public class SearchResultsPage extends AbstractPage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public Set<String> getSetAdvIds() {
        Set<String> allAds = new HashSet<String>();

        for (WebElement we : getDriver().findElements(By.xpath("//tr[starts-with(@id, 'tr_') and not(contains(@id, 'tr_bnr'))]"))) {
            String advId = we.getAttribute("id").replaceFirst("tr_", "");
            allAds.add(advId);
        }
        return allAds;
    }

    public Adv getAdvById(final String id) {
        Adv adv = new Adv(id);
        WebElement we = getDriver().findElement(By.xpath("//tr[@id='tr_" + id + "']"));
        adv.setDescription(we.findElement(By.xpath(".//*[@id='dm_" + id + "']")).getText());
        adv.setAdsCategoryName(we.findElement(By.xpath(".//div[@class='ads_cat_names']")).getText());
        adv.setAdsRegion(we.findElement(By.xpath(".//div[@class='ads_region']")).getText());
        adv.setPrice(we.findElement(By.xpath(".//a[@class='amopt']")).getText().replace(" ", "").replace("€", ""));
        return adv;
    }

    public void selectAdvById(String id) {
        WebElement we = getDriver().findElement(By.xpath("//tr[@id='tr_" + id + "']"));
        if (!we.findElement(By.xpath(".//input[@id='c" + id + "']")).isSelected()) {
            we.findElement(By.xpath(".//input[@id='c" + id + "']")).click();
        }
    }

    public void addToFavorites() {
        getDriver().findElement(By.cssSelector("a#a_fav_sel")).click();
    }

    public void confirmAlert() {
        new WebDriverWait(getDriver(), 2)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a#alert_ok")));
        getDriver().findElement(By.cssSelector("a#alert_ok")).click();
    }

    public void selectRubric(String rubric) {
        Select rubricSelect = new Select(getDriver()
                .findElement(By.cssSelector("div.filter_second_line_dv > span:nth-child(3) > select.filter_sel")));
        rubricSelect.selectByVisibleText(rubric);
    }

    public void sortByPrice() {
        getDriver().findElement(By.xpath("//tr[@id='head_line']/td[2]//a")).click();
    }

    public void gotoExtendedSearch() {
        getDriver().findElement(By.xpath("//table[@id='page_main']//a[contains(@href, '/electronics/search/')]")).click();
    }
}
