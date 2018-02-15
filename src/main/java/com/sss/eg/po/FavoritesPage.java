package com.sss.eg.po;

import com.sss.eg.Adv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.Set;

public class FavoritesPage extends AbstractPage {
    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public Set<String> getSetAdvIds() {
        Set<String> allAds = new HashSet<>();

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
        adv.setAdsCategoryName(we.findElement(By.xpath("./../tr[@id='head_line']/td[@class='msg_column']"))
                .getText()
                .replaceFirst("^.+?:","")
                .trim());
        adv.setPrice(we.findElement(By.xpath(".//a[@class='amopt' and contains(.,'  €')]"))
                .getText()
                .replace(" ", "")
                .replace("€", ""));
        return adv;
    }
}
