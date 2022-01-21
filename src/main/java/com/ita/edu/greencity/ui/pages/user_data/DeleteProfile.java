package com.ita.edu.greencity.ui.pages.user_data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DeleteProfile {

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[1]")
    private WebElement backButton;

    @FindBy(how = How.XPATH, using = "//mat-dialog-actions/button[2]")
    private WebElement deleteButton;

    public void clickOnBackButton(){
        backButton.click();
    }

    public void clickOnDeleteButton(){
        deleteButton.click();
    }
}
