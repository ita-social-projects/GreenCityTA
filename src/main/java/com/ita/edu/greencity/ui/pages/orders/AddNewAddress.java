package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AddNewAddress extends BasePage {


    @FindBy(how = How.XPATH, using = "//h2[@class ='personal-info-pop-up-title']")
    private WebElement newAddressTitle;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'region']")
    private WebElement regionField;

    @FindBy(how = How.XPATH, using = "//input[@id= 'city']")
    private WebElement cityField;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'district']")
    private WebElement districtField;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'district']//option[@class= 'ng-star-inserted']")
    private List<WebElement> listOfDistricts;

    @FindBy(how = How.XPATH, using = "//select[@formcontrolname = 'district']//option[@class= 'ng-star-inserted']")
    private List<WebElement> listOfCities;

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

    public OrderPagePersonalData clickOnAddAddressButton() {
        return new OrderPagePersonalData(driver);
    }

    public OrderPagePersonalData clickOnCancelButton() {
        return new OrderPagePersonalData(driver);
    }

    public AddNewAddress enterAddressComment(final String addressComment) {
        addressCommentField.clear();
        addressCommentField.sendKeys(addressComment);
        return this;
    }

    public AddNewAddress enterEntranceNumber(final String houseCorpus) {
        entranceNumberField.clear();
        entranceNumberField.sendKeys(houseCorpus);
        return this;
    }

    public AddNewAddress enterHouseCorpus(final String houseCorpus) {
        houseCorpusField.clear();
        houseCorpusField.sendKeys(houseCorpus);
        return this;
    }

    public AddNewAddress enterHouseNumber(final String houseNumber) {
        houseNumberField.clear();
        houseNumberField.sendKeys(houseNumber);
        return this;
    }

    public AddNewAddress enterStreet(final String street) {
        streetField.clear();
        streetField.sendKeys(street);
        return this;
    }

    public AddNewAddress chooseCity(int index) {
        clickOnCityField();
        implicitWait(100);
        listOfCities.get(index).click();
        return this;
    }

    public AddNewAddress chooseDistrict(int index) {
        clickOnDistrictField();
        implicitWait(100);
        listOfDistricts.get(index).click();
        return this;
    }

    public AddNewAddress clickOnDistrictField() {
        return this;
    }

    public AddNewAddress clickOnCityField() {
        return this;
    }

    public String getTextFromRegionField() {
        return regionField.getText();
    }

    public String getTextFromNewAddressTitle() {
        return newAddressTitle.getText();
    }

    public AddNewAddress(WebDriver driver) {
        super(driver);
    }
}