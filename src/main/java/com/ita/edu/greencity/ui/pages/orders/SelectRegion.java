package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SelectRegion extends BasePage {

    @FindBy(css = "h5.title-text")
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

    public UbsHomePage clickOnBackButton() {
        getBackButton().click();
        return new UbsHomePage(driver);
    }

    public OrderDetailsPage clickOnContinueButton() {
        waitUntilElementToBeClickable(By.xpath("//button[@class='btn primaryButton primary-global-button']"), 10);
        getContinueButton().click();
        return new OrderDetailsPage(driver);
    }

    public void clickOnRegionDropdown() {
        getRegionDropdown().click();
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

