package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderDetailsPage extends BasePage {

    @FindBy(xpath = "//div[@class='user-location']//h5")
    private WebElement locationTitle;
    @FindBy(xpath = "//button[@class='change-location']")
    private WebElement changeRegionButton;
    @FindBy(xpath = "//select[@name='region']")
    private WebElement changeRegionDropdown;
    @FindBy(xpath = "//select[@name ='region']//option[@class='ng-star-inserted']")
    private List<WebElement> regionButtons;
    @FindBy(xpath = "//input[@id='quantity2']")
    private WebElement NumberOfTextileWaste120lInput;
    @FindBy(xpath = "//input[@id='quantity1']")
    private WebElement NumberOfSafeWasteInput;
    @FindBy(xpath = "//input[@id='quantity3']")
    private WebElement NumberOfTextileWaste20lInput;
    @FindBy(xpath = "//div[@class='main']//span[@class='col-3 bag-name text-right']")
    private List<WebElement> totalPrice;
    @FindBy(xpath = "//div[@class='totalInfo']//strong")
    private List<WebElement> totalAmount;
    @FindBy(xpath = "//input[@class='shadow-none form-control col-12 col-sm-8 my-1 input-border ng-pristine ng-valid ng-touched']")
    private WebElement certificateInput;
    @FindBy(xpath = "//div[@class='validMes ng-star-inserted'][1]//small")
    private WebElement minimumOrderAmountAlert;
    @FindBy(xpath = "//div[@class='validMes ng-star-inserted'][2]//small")
    private WebElement minimumOrderContainsAlert;
    @FindBy(xpath = "//button[@class='primary-global-button btn ng-star-inserted']")
    private WebElement activateCertificateButton;
    @FindBy(xpath = "//div[@class='points']//span[@class='checkmark']")
    private List<WebElement> UseBonusesCheckmarks;
    @FindBy(xpath = "//a[@class='bonus-how-to-link']")
    private WebElement howToGetBonusesButton;
    @FindBy(xpath = "//div[@class='bottom']//span[@class='checkmark']]")
    private List<WebElement> WaitingStoreOrderCheckmarks;
    @FindBy(xpath = "//input[contains(@id,'index')]")
    private List<WebElement> orderNumberInputs;
    @FindBy(xpath = "//button[@class='addOrderBtn']")
    private WebElement addAnotherNumberButton;
    @FindBy(xpath = "//p[@class='link']")
    private WebElement learnAboutPackagesLink;
    @FindBy(xpath = "//textarea[@formcontrolname='orderComment']")
    private WebElement commentInput;
    @FindBy(xpath = "//button[@class='secondary-global-button btn cancel-button btn-main']")
    private WebElement cancelOrderButton;
    @FindBy(xpath = "//button[@class='primary-global-button btn btn-main']")
    private WebElement nextButton;
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }
    public String getLocationFromTitle()
    {
        return locationTitle.getText().substring(11).trim();
    }

    public OrderDetailsPage clickOnChangeRegionButton() {
        changeRegionButton.click();
        return this;
    }

    public OrderDetailsPage clickOnchangeRegionDropdown() {
        changeRegionDropdown.click();
        return this;
    }

    public OrderDetailsPage clickOnRegionByIndex(int index) {
        regionButtons.get(index).click();
        return this;
    }

    public OrderDetailsPage chooseRegionByValue(String value) {
        clickOnchangeRegionDropdown();
        try {
            for (WebElement option : regionButtons) {
                if (option.getText().equals(value.trim()))
                    option.click();
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }

    public OrderDetailsPage EnterNumberOfTextileWaste120lInput(String value) {
        NumberOfTextileWaste120lInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public OrderDetailsPage EnterNumberOfSafeWasteInput(String value) {
        NumberOfSafeWasteInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public OrderDetailsPage EnterNumberOfTextileWaste20lInput(String value) {
        NumberOfTextileWaste20lInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public String getTextileWaste120lSum() {
        String[] arr = totalPrice.get(0).getText().split("/s");
        return arr[0];
    }

    public String getSaveWasteSum() {
        String[] arr = totalPrice.get(1).getText().split("/s");
        return arr[0];
    }

    public String getTextileWaste20lSum() {
        String[] arr = totalPrice.get(2).getText().split("/s");
        return arr[0];
    }

    public String getOrderAmount() {
        String[] arr = totalAmount.get(0).getText().split("/s");
        return arr[0];
    }

    public String getAmountDue() {
        String[] arr = totalAmount.get(1).getText().split("/s");
        return arr[0];
    }

    public String getMinimumOrderAmountAlertText() {
        String text = minimumOrderAmountAlert.getText();
        return text;
    }

    public String getMinimumOrderContainsAlertText() {
        String text = minimumOrderContainsAlert.getText();
        return text;
    }

    public OrderDetailsPage EnterCertificateInput(String value) {
        certificateInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public OrderDetailsPage clickOnActivateCertificateButton() {
        activateCertificateButton.click();
        return this;
    }

    public OrderDetailsPage ClickOnNoUseBonusesCheckmark() {
        UseBonusesCheckmarks.get(0).click();
        return this;
    }

    public OrderDetailsPage ClickOnYesUseBonusesCheckmark() {
        UseBonusesCheckmarks.get(1).click();
        return this;
    }

    public OrderDetailsPage ClickOnNoWaitingStoreOrderCheckmark() {
        WaitingStoreOrderCheckmarks.get(0).click();
        return this;
    }

    public OrderDetailsPage ClickOnYesWaitingStoreOrderCheckmark() {
        WaitingStoreOrderCheckmarks.get(1).click();
        return this;
    }

    public OrderDetailsPage clickOnHowToGetBonusesButton() {
        howToGetBonusesButton.click();
        return this;
    }

    public OrderDetailsPage EnterOrderNumberInputs(String MessageText, int value) {
        orderNumberInputs.get(value).sendKeys(MessageText);
        return this;
    }

    public OrderDetailsPage clickOnAddAnotherNumberButton() {
        addAnotherNumberButton.click();
        return this;
    }

    public OrderDetailsPage clickOnLearnAboutPackagesLink() {
        learnAboutPackagesLink.click();
        return this;
    }

    public OrderDetailsPage EnterCommentInput(String value) {
        commentInput.sendKeys(value, Keys.ENTER);
        return this;
    }

    public String getCommentInput() {
        return commentInput.getText();
    }

    public OrderDetailsPage clickOnCancelOrderButton() {
        cancelOrderButton.click();
        return this;
    }

    public OrderPagePersonalData clickOnNextButton() {
        nextButton.click();
        return new OrderPagePersonalData(driver);
    }


}
