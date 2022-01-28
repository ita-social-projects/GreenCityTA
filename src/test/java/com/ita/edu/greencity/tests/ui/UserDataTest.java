package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
    @DataProvider(name = "PhoneNumberProvider")
    public Object[][] dpArrayOutputTest() {
        return new Object[][]{
                {"0970101011", "+380 (97) 010 10 11"},
                {"0972222222", "+380 (97) 222 22 22"},
        };
    }

    @Test(dataProvider = "PhoneNumberProvider")
    public void editPhone(String newNumber, String expectedNumber ) {
        HeaderSignedInComponent ubs = new HeaderSignedInComponent(driver);
        ubs.clickUserMenu().clickUbsUser().getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnEditDataButton()
                .enterEditedPhone(newNumber)
                .clickOnSaveChangesButton();
        UserData userData = new UserData(driver);
        String actual = userData.getTextFromPhoneField();
        Assert.assertEquals(actual, expectedNumber);
    }
}
