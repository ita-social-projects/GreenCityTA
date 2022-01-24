package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDataTest extends TestRun {
    @Test
    public void editPhone() {

        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByIndex(1)
                .clickOnContinueButton();

        String newNumber = "0970101011";
        HeaderSignedInComponent ubs = new HeaderSignedInComponent(driver);
        ubs.clickUserMenu().getUbsUserPage().clickOnUserDataButton()
                .clickOnEditDataButton()
                .enterEditedPhone(newNumber)
                .clickOnSaveChangesButton();
        UserData userData = new UserData(driver);
        String actual = userData.getTextFromPhoneField();
        Assert.assertEquals(actual, newNumber);


    }
}