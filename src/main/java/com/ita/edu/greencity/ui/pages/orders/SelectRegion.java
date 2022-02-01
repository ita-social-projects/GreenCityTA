package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectRegion extends BasePage {

    @FindBy(xpath = "//div[@class='title']/h5")
    private WebElement title;
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
    @FindBy(xpath = "//p[@class='text ng-star-inserted']")
    private WebElement bottomText;

    public SelectRegion(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        this.sleep(7000);
        return title.getText();
    }
    public WebElement getCloseButton() {
        return closeButton;
    }

    public String getBottomText() {
        return bottomText.getText();
    }

    public String getContinueButtonText() {
        return continueButton.getText();
    }

    public String getBackButtonText() {
        return backButton.getText();
    }

    public void clickOnCloseButton() {
        closeButton.click();
    }

    public void clickOnBackButton() {
        backButton.click();
    }

    public OrderDetailsPage clickOnContinueButton() {
        waitUntilElementToBeClickable(By.xpath("//button[@class='btn primaryButton primary-global-button']"), 10);
        continueButton.click();
        return new OrderDetailsPage(driver);
    }

    public void clickOnRegionDropdown() {
        regionDropdown.click();
    }

    public SelectRegion chooseRegionByIndex(int index) {
        sleep(5000);
        clickOnRegionDropdown();
        listOfRegions.get(index).click();
        return this;
    }

    public SelectRegion chooseRegionByValue(String value) {
        sleep(7000);
        clickOnRegionDropdown();
        for (WebElement option : listOfRegions) {
            if (option.getText().equals(value.trim())) {
                option.click();
                break;
            }
        }
        return this;
    }
}

