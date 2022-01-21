package com.ita.edu.greencity.ui.pages.user_data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserData {

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

    public void clickOnEditDataButton(){
        editData.click();
    }
    public String getTextFromEmailField(){
        return email.getText();
    }
    public String getTextFromPhoneField(){
        return phone.getText();
    }
    public void clickOnChangePasswordButton(){
        changePassword.click();
    }
    public void clickOnDeleteProfileButton(){
        deleteProfile.click();
    }


}

