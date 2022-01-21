package com.ita.edu.greencity.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void implicitWait(long ms){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(ms));
    }

    public void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementToBeClickable(By locator, long seconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
