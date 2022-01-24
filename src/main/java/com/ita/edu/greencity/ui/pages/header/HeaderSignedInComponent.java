package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import com.ita.edu.greencity.ui.pages.user_data.EditUserData;
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

    public UbsUserOrders clickUserMenu() {
        userMenu.click();
        return new UbsUserOrders(driver);
    }

    public EditUserData clickSettings() {
        settings.click();
        return new EditUserData(driver);
    }

    public UbsHomePage clickSignOut() {
        signOut.click();
        return new UbsHomePage(driver);
    }

    public void clickUbsUser() {
        ubsUser.click();
    }

    public String getUserName(WebElement userMenu){
        return userMenu.getText();
    }
}
