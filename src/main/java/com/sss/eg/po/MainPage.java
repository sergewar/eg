package com.sss.eg.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void gotoElectronicsSection() {
        getDriver().findElement(By.xpath("//a[@id='ic6']/../div[@class='main_head']")).click();
    }
}
