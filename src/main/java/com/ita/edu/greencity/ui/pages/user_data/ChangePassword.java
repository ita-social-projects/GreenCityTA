package com.ita.edu.greencity.ui.pages.user_data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePassword {

    @FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-3\"]")
    private WebElement oldPassword;

    @FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-4\"]")
    private WebElement newPassword;

    @FindBy(how = How.XPATH, using = "//*[@id=\"mat-input-5\"]]")
    private WebElement repeatNewPassword;

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[2]")
    private WebElement changePasswordButton;

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[1]")
    private WebElement cancelChangingPasswordButton;

    public void enterOldPassword(final String oldPasswordData){
        oldPassword.clear();
        oldPassword.sendKeys(oldPasswordData);
    }

    public void enterNewPassword(final String newPasswordData){
        newPassword.clear();
        newPassword.sendKeys(newPasswordData);
    }

    public void enterRepeatNewPassword(final String repeatPasswordData){
        repeatNewPassword.clear();
        repeatNewPassword.sendKeys(repeatPasswordData);
    }

    public void clickOnChangePasswordButton(){
        changePasswordButton.click();
    }

    public void clickOnCancelChangingPasswordButton(){
        cancelChangingPasswordButton.click();
    }




}
