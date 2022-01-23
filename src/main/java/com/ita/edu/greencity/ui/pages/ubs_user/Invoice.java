package com.ita.edu.greencity.ui.pages.ubs_user;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Invoice extends BasePage {

    public Invoice(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "more-about-bonuses-link")
    private WebElement findOutWhatBonusesAreAccruedForButton;

    public void clickOnFindOutWhatBonusesAreAccruedForButton() {
        findOutWhatBonusesAreAccruedForButton.click();
    }

    public UbsUser getUbsUserPage() {
        return new UbsUser(driver);
    }
}
