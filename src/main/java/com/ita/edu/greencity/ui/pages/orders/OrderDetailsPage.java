package com.ita.edu.greencity.ui.pages.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OrderDetailsPage  {
    WebDriver driver;

    public OrderDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(how = How.XPATH, using = "//button[@class='change-location']")
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
    @FindBy(xpath = "input[@class='shadow-none form-control col-12 col-sm-8 my-1 input-border ng-pristine ng-valid ng-touched']")
    private WebElement certificateInput;
    @FindBy(xpath = "button[@class='primary-global-button btn ng-star-inserted']")
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
    @FindBy(xpath = "p[@class='link']")
    private WebElement learnAboutPackagesLink;
    @FindBy(xpath = "textarea[@formcontrolname='orderComment']")
    private WebElement commentInput;
    @FindBy(xpath = "//button[@class='secondary-global-button btn cancel-button btn-main']")
    private WebElement cancelOrderButton;
    @FindBy(xpath = "//button[@class='primary-global-button btn btn-main']")
    private WebElement nextButton;

    public void clickOnChangeRegionButton(){
        changeRegionButton.click();
    }
    public void clickOnchangeRegionDropdown(){
        changeRegionDropdown.click();
    }
    public void clickOnRegionButtons(int value){
        regionButtons.get(value).click();
    }
    public void EnterNumberOfTextileWaste120lInput(String value){
        NumberOfTextileWaste120lInput.click();
        NumberOfTextileWaste120lInput.clear();
        NumberOfTextileWaste120lInput.sendKeys(value);
    }
    public void EnterNumberOfSafeWasteInput(String value){
        NumberOfSafeWasteInput.click();
        NumberOfSafeWasteInput.clear();
        NumberOfSafeWasteInput.sendKeys(value);
    }
    public void EnterNumberOfTextileWaste20lInput(String value){
        NumberOfTextileWaste20lInput.click();
        NumberOfTextileWaste20lInput.clear();
        NumberOfTextileWaste20lInput.sendKeys(value);
    }
    public void EnterCertificateInput(String value){
        certificateInput.click();
        certificateInput.clear();
        certificateInput.sendKeys(value);
    }
    public void clickOnActivateCertificateButton(){
        activateCertificateButton.click();
    }

    public void ClickOnNoUseBonusesCheckmark(){
        UseBonusesCheckmarks.get(0).click();
    }
    public void ClickOnYesUseBonusesCheckmark(){
        UseBonusesCheckmarks.get(1).click();
    }
    public void ClickOnNoWaitingStoreOrderCheckmark(){
        WaitingStoreOrderCheckmarks.get(0).click();
    }
    public void ClickOnYesWaitingStoreOrderCheckmark(){
        WaitingStoreOrderCheckmarks.get(1).click();
    }
    public void clickOnHowToGetBonusesButton(){
        howToGetBonusesButton.click();
    }
    public void EnterOrderNumberInputs(String MessageText,int value){
        orderNumberInputs.get(value).click();
        orderNumberInputs.get(value).clear();
        orderNumberInputs.get(value).sendKeys(MessageText);
    }
    public void clickOnAddAnotherNumberButton(){
        addAnotherNumberButton.click();
    }
    public void clickOnLearnAboutPackagesLink(){
        learnAboutPackagesLink.click();
    }
    public void EnterCommentInput(String value){
        commentInput.click();
        commentInput.clear();
        commentInput.sendKeys(value);
    }
    public void clickOnCancelOrderButton(){
        cancelOrderButton.click();
    }
    public void clickOnNextButton(){
        nextButton.click();
    }



}
