package com.sss.eg.po;

import com.sss.eg.Rubric;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class SearchCriteriaPage extends AbstractPage {

    public SearchCriteriaPage(WebDriver driver) {
        super(driver);
    }

    public void setSearchText(String text) {
        getDriver().findElement(By.cssSelector("input#ptxt")).sendKeys(text);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new WebDriverWait(getDriver(), 2)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='preload_txt']/div[.='" + text + "']/..")));
        if (isElementExist(getDriver().findElement(By.xpath("//div[@id='preload_txt']/div[.='" + text + "']")))) {
            getDriver().findElement(By.xpath("//div[@id='preload_txt']/div[.='" + text + "']")).click();
        }
    }

    public void submitSearch() {
        getDriver().findElement(By.cssSelector("input#sbtn")).click();
    }

    public void setPriceRange(int min, int max) {
        WebElement minElement = getDriver().findElement(By.name("topt[8][min]"));
        WebElement maxElement = getDriver().findElement(By.name("topt[8][max]"));
        minElement.clear();
        minElement.sendKeys("" + min);
        maxElement.clear();
        maxElement.sendKeys("" + max);
    }

    public void selectRubric(Rubric rubric) {
        Select rubricSelect = new Select(getDriver().findElement(By.name("sid")));
        String value;
        if (Rubric.SALE.equals(rubric)) {
            value = "1";
        } else if (Rubric.BUY.equals(rubric)) {
            value = "2";
        } else if (Rubric.ANY.equals(rubric)) {
            value = "5";
        } else {
            throw new IllegalArgumentException("wrong rubric: " + rubric);
        }
        rubricSelect.selectByValue(value);
    }

    public void selectPeriod(int days) {
        Integer[] availablePeriod = {0, 1, 3, 7, 30};
        if (Arrays.asList(availablePeriod).contains(days)) {
            Select periodSelect = new Select(getDriver().findElement(By.name("pr")));
            periodSelect.selectByValue("" + days);
        } else {
            throw new IllegalArgumentException("wrong period days: " + days + " should be in: " + Arrays.asList(availablePeriod).toString());
        }
    }
}
