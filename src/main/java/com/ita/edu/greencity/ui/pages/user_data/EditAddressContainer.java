package com.ita.edu.greencity.ui.pages.user_data;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class EditAddressContainer {
    private WebDriver driver;

    private DefaultElementLocatorFactory parentContext;

    private WebElement rootElement;

    public EditAddressContainer(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'city')]")
    private WebElement city;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'region')]")
    private WebElement region;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'district')]")
    private WebElement district;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'street')]")
    private WebElement street;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'houseNumber')]")
    private WebElement houseNumber;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'houseCorpus')]")
    private WebElement houseCorpus;

    @FindBy(how = How.XPATH, using = ".//*[contains(@formcontrolname, 'entranceNumber')]")
    private WebElement entranceNumber;

    @FindBy(how = How.CSS, using = "h5")
    private WebElement addressNumber;
//????????????????????????????????
    @FindBy(how = How.XPATH, using = "//app-ubs-user-profile-page/div/div/div/div[4]/button[2]")
    private WebElement  saveChanges;

    public EditAddressContainer setCityAddress(final String newData) {
        city.clear();
        city.sendKeys(newData);
        return this;
    }

    public EditAddressContainer setRegionAddress(final String newData) {
        region.clear();
        region.sendKeys(newData);
        return this;
    }

    public EditAddressContainer setDistrictAddress(final String newData) {
        district.clear();
        district.sendKeys(newData);
        return this;
    }

    public EditAddressContainer setStreetAddress(final String newData) {
        street.clear();
        street.sendKeys(newData);
        return this;
    }

    public EditAddressContainer setHouseNumberAddress(final String newData) {
        houseNumber.clear();
        houseNumber.sendKeys(newData);
        return this;
    }

    public EditAddressContainer setHouseCorpusAddress(final String newData) {
        houseCorpus.clear();
        houseCorpus.sendKeys(newData);
        return this;
    }


    public EditAddressContainer setEntranceNumberAddress(final String newData) {
        entranceNumber.clear();
        entranceNumber.sendKeys(newData);
        return this;
    }

    public UserData clickOnSaveChangesButton(){
        saveChanges.click();
        return new UserData(driver);
    }

    public String getAddressNumber() {
        return addressNumber.getText();
    }

}
