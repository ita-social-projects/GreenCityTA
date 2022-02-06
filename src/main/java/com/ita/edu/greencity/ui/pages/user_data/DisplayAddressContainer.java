package com.ita.edu.greencity.ui.pages.user_data;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class DisplayAddressContainer {

    public DisplayAddressContainer(WebElement rootElement) {
        DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @FindBy(how = How.CSS, using = "p.ng-star-inserted")
    private List<WebElement> dataAddressTable;

    @FindBy(how = How.CSS, using = "h5")
    private WebElement addressNumberShow;

    @Step("get address number data")
    public String getAddressNumberShow() {
        return addressNumberShow.getText();
    }

    @Step("get data from 'city' field on UserData page")
    public String getCity() {
        return dataAddressTable.get(0).getText();
    }

    @Step("get data from 'region' field on UserData page")
    public String getRegion() {
        return dataAddressTable.get(1).getText();
    }

    @Step("get data from 'district' field on UserData page")
    public String getDistrict() {
        return dataAddressTable.get(2).getText();
    }

    @Step("get data from 'street' field on UserData page")
    public String getStreet() {
        return dataAddressTable.get(3).getText();
    }

    @Step("get data from 'house' field on UserData page")
    public String getHouse() {
        return dataAddressTable.get(4).getText();
    }

    @Step("get data from 'corpus' field on UserData page")
    public String getCorpus() {
        return dataAddressTable.get(5).getText();
    }

    @Step("get data from 'entrance' field on UserData page")
    public String getEntrance() {
        return dataAddressTable.get(6).getText();
    }


}
