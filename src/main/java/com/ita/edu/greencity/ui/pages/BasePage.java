package com.ita.edu.greencity.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void implicitWait(long ms) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(ms));
    }

    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementToBeClickable(By locator, long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void switchToNewTab() {
        ArrayList<String> tabs2 = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(0));
        getDriver().close();
        getDriver().switchTo().window(tabs2.get(1));
    }
}
