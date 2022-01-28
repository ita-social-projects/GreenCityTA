package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserDataTest extends TestRun {
    @BeforeMethod
    public void loginToUBS(){
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }
    @Test
    public void editPhone() throws InterruptedException {
        String newNumber = "0970101011";
        HeaderSignedInComponent ubs = new HeaderSignedInComponent(driver);
        ubs.clickUserMenu().clickUbsUser().getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnEditDataButton()
                .enterEditedPhone(newNumber)
                .clickOnSaveChangesButton();
        UserData userData = new UserData(driver);
        String expected = "+380 (97) 010 10 11";
        String actual = userData.getTextFromPhoneField();
        Assert.assertEquals(actual, expected);

    }
}
