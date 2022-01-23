package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.backgroundservice.BackgroundService;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UserData extends BasePage {

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.edit")
    private WebElement editData;

    @FindBy(how = How.CSS, using = "div.contacts.inline > div:nth-child(1) > p")
    private WebElement email;

    @FindBy(how = How.CSS, using = "div.contacts.inline > div:nth-child(2) > p")
    private WebElement phone;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.open")
    private WebElement changePassword;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.delete")
    private WebElement deleteProfile;

    public UserData(WebDriver driver) {
        super(driver);
    }

    public UserData clickOnEditDataButton(){
        editData.click();
        return this;
    }
    public UserData getTextFromEmailField(){
        email.getText();
        return this;
    }
    public UserData getTextFromPhoneField(){
        phone.getText();
        return this;
    }
    public ChangePassword clickOnChangePasswordButton(){
        changePassword.click();
        return new ChangePassword(driver);
    }
    public DeleteProfile clickOnDeleteProfileButton(){
        deleteProfile.click();
        return new DeleteProfile(driver);
    }


}

