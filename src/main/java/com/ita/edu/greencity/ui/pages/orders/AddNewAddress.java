package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AddNewAddress extends BasePage {


    @FindBy(how = How.XPATH, using = "//h2[@class ='personal-info-pop-up-title']")
    private WebElement newAddressTitle;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'region')]")
    private WebElement regionField;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'city')]")
    private WebElement cityField;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'district')]")
    private WebElement districtField;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'district')]//option")
    private List<WebElement> listOfDistricts;

    @FindBy(how = How.XPATH, using = "//select[contains(@formcontrolname, 'city')]//option[@class= 'ng-star-inserted']")
    private List<WebElement> listOfCities;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'street')]")
    private WebElement streetField;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'houseNumber')]")
    private WebElement houseNumberField;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'houseCorpus')]")
    private WebElement houseCorpusField;

    @FindBy(how = How.XPATH, using = "//input[contains(@formcontrolname, 'entranceNumber')]")
    private WebElement entranceNumberField;

    @FindBy(how = How.XPATH, using = "//div[@class = 'address-comment']//textarea[@formcontrolname= 'addressComment']")
    private WebElement addressCommentField;

    @FindBy(how = How.XPATH, using = "//button[@class = 'secondary-global-button btn m-0 mr-2']")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//button[@class = 'btn add-address']")
    private WebElement addAddressButton;

    @FindBy(how = How.XPATH, using = "//span[@class='pac-matched']")
    private List<WebElement> listOfStreet;




    public AddNewAddress chooseStreet(int index){
        waitUntilElementToBeClickable(By.xpath("//span[@class='pac-matched']"),10);
        listOfStreet.get(index).click();
        return this;
    }

    public AddNewAddress(WebDriver driver) {
        super(driver);
    }

    public WebElement getAddAddressButton() {
        return addAddressButton;
    }

    public OrderPagePersonalData clickOnAddAddressButton() {
        return new OrderPagePersonalData(driver);
    }

    public OrderPagePersonalData clickOnCancelButton() {
        return new OrderPagePersonalData(driver);
    }

    public AddNewAddress enterAddressComment(final String addressComment) {
        addressCommentField.clear();
        addressCommentField.sendKeys(addressComment, Keys.ENTER);
        return this;
    }

    public AddNewAddress enterEntranceNumber(final String houseCorpus) {
        entranceNumberField.clear();
        entranceNumberField.sendKeys(houseCorpus, Keys.ENTER);
        return this;
    }

    public AddNewAddress enterHouseCorpus(final String houseCorpus) {
        houseCorpusField.clear();
        houseCorpusField.sendKeys(houseCorpus, Keys.ENTER);
        return this;
    }

    public AddNewAddress enterHouseNumber(final String houseNumber) {
        houseNumberField.clear();
        houseNumberField.sendKeys(houseNumber, Keys.ENTER);
        return this;
    }

    public AddNewAddress enterStreet(final String street) throws InterruptedException {
        streetField.clear();
        streetField.sendKeys(street);
        return this;
    }

    public AddNewAddress chooseCity(int index) {
        clickOnCityField();
        listOfCities.get(index).click();
        return this;
    }

    public AddNewAddress chooseDistrict(int index) {
        clickOnDistrictField();
        listOfDistricts.get(index).click();
        return this;
    }

    public AddNewAddress clickOnDistrictField() {
        districtField.click();
        return this;
    }

    public AddNewAddress clickOnCityField() {
        cityField.click();
        return this;
    }

    public String getTextFromRegionField() {
        return regionField.getText();
    }

    public String getTextFromNewAddressTitle() {
        return newAddressTitle.getText();
    }

    public boolean isClickable()
    {
        try
        {
          waitUntilElementToBeClickable(By.xpath("//button[@class = 'btn add-address']"),2);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}