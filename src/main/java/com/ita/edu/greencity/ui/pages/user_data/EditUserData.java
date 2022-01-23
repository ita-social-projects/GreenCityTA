package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditUserData extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id=\"recipientName\"]")
    private WebElement editName;

    @FindBy(how = How.XPATH, using = "//*[@id=\"recipientSurname\"]")
    private WebElement editSurname;

    @FindBy(how = How.XPATH, using = "//*[@id=\"recipientPhone\"]")
    private WebElement editPhone;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.cancel")
    private WebElement discardChanges;

    @FindBy(how = How.CSS, using = "button.btn.btn-success")
    private WebElement  saveChanges;

    public EditUserData(WebDriver driver) {
        super(driver);
    }

    public EditUserData enterEditedName(final String newName){
        editName.clear();
        editName.sendKeys(newName);
        return this;
    }

    public EditUserData enterEditedSurname(final String newSurname){
        editSurname.clear();
        editSurname.sendKeys(newSurname);
        return this;
    }

    public EditUserData enterEditedPhone(final String newPhone){
        editPhone.clear();
        editPhone.sendKeys(newPhone);
        return this;
    }

    public UserData clickOnDiscardChangesButton(){
        discardChanges.click();
        return new UserData(driver);
    }

    public UserData clickOnSaveChangesButton(){
        saveChanges.click();
        return new UserData(driver);
    }

}
