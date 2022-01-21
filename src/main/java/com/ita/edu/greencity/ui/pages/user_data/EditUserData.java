package com.ita.edu.greencity.ui.pages.user_data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditUserData {
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

    public void enterEditedName(final String newName){
        editName.clear();
        editName.sendKeys(newName);
    }

    public void enterEditedSurname(final String newSurname){
        editSurname.clear();
        editSurname.sendKeys(newSurname);
    }

    public void enterEditedPhone(final String newPhone){
        editPhone.clear();
        editPhone.sendKeys(newPhone);
    }

    public void clickOnDiscardChangesButton(){
        discardChanges.click();
    }

    public void clickOnSaveChangesButton(){
        saveChanges.click();
    }

}
