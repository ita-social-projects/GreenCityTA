package com.ita.edu.greencity.ui.pages.header;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderSignedInComponent extends HeaderComponent {

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[1]/a")
    private WebElement userMenu;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[2]/a")
    private WebElement settings;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[3]/a")
    private WebElement signOut;

    @FindBy(how = How.XPATH, using = "//*[@id='header_user-wrp']/li[4]/a")
    private WebElement ubsUser;

    public HeaderSignedInComponent(WebDriver driver) {
        super(driver);
    }

    public void clickUserMenu() {
        userMenu.click();
    }

    public void clickSettings() {
        settings.click();
    }

    public void clickSignOut() {
        signOut.click();
    }

    public void clickUbsUser() {
        ubsUser.click();
    }

    public String getUserName(WebElement userMenu){
        return userMenu.getText();
    }
}
