package com.ita.edu.greencity.ui.pages.ubs_homepage;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UbsHomePage extends BasePage{
    @FindBy(how = How.CSS, using = "span.header_lang-switcher-wrp.header_navigation-menu-right-list")
    private WebElement langButton;

    @FindBy(how = How.CSS, using = "span.add-shadow.header_lang-switcher-wrp.header_navigation-menu-right-list")
    private WebElement selectLang;

    @FindBy(how = How.XPATH, using = "//h1")
    private WebElement h1Text;

//    @FindBy(linkText= " call-up the courier ")
//    private WebElement orderCourier;

    @FindBy(how = How.CSS, using = "button.primary-global-button")
    private WebElement orderCourier;

    public UbsHomePage(WebDriver driver) {
        super(driver);
    }


//_________________________________________________________

    public void pressLangButton() {
        langButton.click();
    }

    public void pressSelectLang() {
        selectLang.click();
    }

    public String getTitleH1Text() {
        return h1Text.getText();
    }

    public SignInComponent pressOrderCourier() {
        orderCourier.click();
        return new SignInComponent(driver);
    }






}
