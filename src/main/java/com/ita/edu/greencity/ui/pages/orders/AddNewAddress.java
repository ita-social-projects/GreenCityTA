package com.ita.edu.greencity.ui.pages.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.List;

public class AddNewAddress {

    protected WebDriver driver;

    @FindBy(how = How.XPATH, using = "//h2[@class ='personal-info-pop-up-title']")
    private WebElement newAddressTitle;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'region']")
    private WebElement regionField;

    @FindBy(how = How.XPATH, using = "//input[@id= 'city']")
    private WebElement cityField;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'district']")
    private WebElement districtField;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'district']//option[@class= 'ng-star-inserted']")
    private List <WebElement> listOfDistrict;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname = 'street']")
    private WebElement streetField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname = 'houseNumber']")
    private WebElement houseNumberField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname = 'houseCorpus']")
    private WebElement houseCorpusField;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname = 'entranceNumber']")
    private WebElement entranceNumberField;

    @FindBy(how = How.XPATH, using = "//div[@class = 'address-comment']//textarea[@formcontrolname= 'addressComment']")
    private WebElement addressCommentField;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn m-0 mr-2']")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//div[@class = 'mat-dialog-actions d-flex justify-content-end buttons']//button[@class = 'primary-global-button btn m-0']")
    private WebElement addAddressButton;

    public void clickOnAddAddressButton(){
        addAddressButton.click();
    }

    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public void enterAddressComment(final String addressComment){
        addressCommentField.clear();
        addressCommentField.sendKeys(addressComment);
    }

    public void enterEntranceNumber(final String houseCorpus){
        entranceNumberField.clear();
        entranceNumberField.sendKeys(houseCorpus);
    }

    public void enterHouseCorpus(final String houseCorpus){
        houseCorpusField.clear();
        houseCorpusField.sendKeys(houseCorpus);
    }

    public void enterHouseNumber(final String houseNumber){
        houseNumberField.clear();
        houseNumberField.sendKeys(houseNumber);
    }

    public void enterStreet(final String street){
        streetField.clear();
        streetField.sendKeys(street);
    }

    public void chooseDistrict(){
        clickOnDistrictField();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        listOfDistrict.get(2).click();
    }

    public void clickOnDistrictField(){
        districtField.click();
    }

    public void enterCity(final String city){
        cityField.clear();
        cityField.sendKeys(city);
    }

    public String getTextFromRegionField(){
        return regionField.getText();
    }

    public String getTextFromNewAddressTitle(){
        return newAddressTitle.getText();
    }
}
