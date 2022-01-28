package com.ita.edu.greencity.ui.pages.user_data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

public class DisplayAddressContainer {
    private WebDriver driver;

    private DefaultElementLocatorFactory parentContext;

    private WebElement rootElement;

    public DisplayAddressContainer(WebDriver driver, WebElement rootElement) {
        this.rootElement = rootElement;
        this.driver = driver;
        parentContext = new DefaultElementLocatorFactory(rootElement);
        PageFactory.initElements(parentContext, this);
    }

    @FindBy(how = How.CSS, using = "p.ng-star-inserted")
    private List<WebElement> dataAddressTable;

    @FindBy(how = How.CSS, using = "h5")
    private WebElement addressNumberShow;

    public String getAddressNumberShow() {
        return addressNumberShow.getText();
    }

    public String getCity() {
        return dataAddressTable.get(0).getText();
    }

    public String getRegion() {
        return dataAddressTable.get(1).getText();
    }

    public String getDistrict() {
        return dataAddressTable.get(2).getText();
    }
    public String getStreet() {
        return dataAddressTable.get(3).getText();
    }

    public String getHouse() {
        return dataAddressTable.get(4).getText();
    }

    public String getCorpus() {
        return dataAddressTable.get(5).getText();
    }

    public String getEntrance() {
        return dataAddressTable.get(6).getText();
    }

    public String getAdressData(String  numberAdress, String elem) {
        return switch (elem) {
            case "city" -> dataAddressTable.get(0).getText();
            case "region" -> dataAddressTable.get(1).getText();
            case "district" -> dataAddressTable.get(2).getText();
            case "street" -> dataAddressTable.get(3).getText();
            case "house" -> dataAddressTable.get(4).getText();
            case "corpus" -> dataAddressTable.get(5).getText();
            case "entrance" -> dataAddressTable.get(6).getText();
            default -> null;
        };
    }


}
