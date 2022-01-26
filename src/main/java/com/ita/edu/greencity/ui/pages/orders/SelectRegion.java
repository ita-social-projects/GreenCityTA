package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

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

    public static boolean listContainsExactValue(List<WebElement> arr, String value) {
        boolean ch = false;
        for (WebElement el : arr) {
            if (el.getText().equals(value)) {
                ch = true;
                break;
            }
        }
        return ch;
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
        waitUntilElementToBeClickable(By.xpath("//button[@class='btn primaryButton primary-global-button']"),10);
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
            if (!listContainsExactValue(listOfRegions, value.trim())) {
                throw new NoSuchOptionException();
            }
            for (WebElement option : listOfRegions) {
                if (option.getText().equals(value.trim())) {
                    option.click();
                    break;
                }
            }
        } catch (NoSuchOptionException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }
}
class NoSuchOptionException extends Exception {
    public NoSuchOptionException() {
        super("Incorrect value to select! This option does not exist!");
    }
}
