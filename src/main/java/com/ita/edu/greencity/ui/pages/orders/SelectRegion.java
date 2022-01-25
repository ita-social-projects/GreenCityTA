package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class SelectRegion extends BasePage {

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

    public SelectRegion(WebDriver driver) {
        super(driver);
    }


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

    public OrderDetailsPage clickOnContinueButton() {
        getContinueButton().click();
        return new OrderDetailsPage(driver);
    }

    public void clickOnRegionDropdown() {
        getRegionDropdown().click();
    }

    public SelectRegion chooseRegionByIndex(int index) {
        clickOnRegionDropdown();
        try {
            listOfRegions.get(index).click();
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }

    public SelectRegion chooseRegionByValue(String value) {
        clickOnRegionDropdown();
        try {
            for (WebElement option : listOfRegions) {
                if (option.getText().equals(value.trim())) {
                    waitUntilWebElementToBeClickableBy(option,1000);
                    option.click();
                }
                break;
            }
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }

}
