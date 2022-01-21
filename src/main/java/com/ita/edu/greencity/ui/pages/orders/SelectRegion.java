package com.ita.edu.greencity.ui.pages.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class SelectRegion {
    private WebDriver driver;

    public SelectRegion(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@class = 'close-btn']")
    private WebElement closeButton;

    @FindBy(xpath = "//button[@class='btn secondaryButton secondary-global-button']")
    private WebElement backButton;

    @FindBy(xpath = "//button[@class='btn primaryButton primary-global-button']")
    private WebElement continueButton;

    @FindBy(xpath = "//select[@name='region']")
    private WebElement regionDropdown;

    @FindBy(xpath = "//select[@name='region']/option[@class ='ng-star-inserted']")
    private List<WebElement> listOfRegions;

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getContinueButton() {
        return continueButton;
    }

    public WebElement getRegionDropdown() {
        return regionDropdown;
    }

    public void clickOnCloseButton() {
        getCloseButton().click();
    }

    public void clickOnBackButton() {
        getBackButton().click();
    }

    public void clickOnContinueButton() {
        getContinueButton().click();
    }

    public void clickOnRegionDropdown() {
        getRegionDropdown().click();
    }

    public SelectRegion chooseRegionByIndex(int index) {
        clickOnRegionDropdown();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            listOfRegions.get(index).click();
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    public SelectRegion chooseRegionByValue(String value) {
        clickOnRegionDropdown();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            for (WebElement option : listOfRegions) {
                if (option.getText().contains(value.trim()))
                    option.click();
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

}
