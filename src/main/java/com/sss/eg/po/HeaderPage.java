package com.sss.eg.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage extends AbstractPage {
    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void switchToRuLang() {
        if ("RU".equals(getDriver().findElement(By.cssSelector("span.menu_lang a.a_menu")).getText())) {
            getDriver().findElement(By.cssSelector("span.menu_lang a.a_menu")).click();
        }
    }

    public void switchToLvLang() {
        if ("LV".equals(getDriver().findElement(By.cssSelector("span.menu_lang a.a_menu")).getText())) {
            getDriver().findElement(By.cssSelector("span.menu_lang a.a_menu")).click();
        }
    }

    public void gotoSearchCriteriaPage() {
        getDriver().findElement(By.xpath("//a[contains(@href,'/search/') and @class='a_menu']")).click();
    }

    public void gotoFavorites() {
        getDriver().findElement(By.xpath("//a[contains(@href,'/favorites/') and @class='a_menu']")).click();
    }
}
