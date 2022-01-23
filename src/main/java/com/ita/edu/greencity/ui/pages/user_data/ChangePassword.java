package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ChangePassword extends BasePage {

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

    public ChangePassword(WebDriver driver) {
        super(driver);
    }

    public ChangePassword enterOldPassword(final String oldPasswordData){
        oldPassword.clear();
        oldPassword.sendKeys(oldPasswordData);
        return this;
    }

    public ChangePassword enterNewPassword(final String newPasswordData){
        newPassword.clear();
        newPassword.sendKeys(newPasswordData);
        return this;
    }

    public ChangePassword enterRepeatNewPassword(final String repeatPasswordData){
        repeatNewPassword.clear();
        repeatNewPassword.sendKeys(repeatPasswordData);
        return this;
    }

    public UserData clickOnChangePasswordButton(){
        changePasswordButton.click();
        return new UserData(driver);
    }

    public UserData clickOnCancelChangingPasswordButton(){
        cancelChangingPasswordButton.click();
        return new UserData(driver);
    }




}
