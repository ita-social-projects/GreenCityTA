package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.IOException;

public class ChangePasswordTest extends TestRun {
    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }

    @DataProvider(name = "dataProvider")
    private Object[][] dataProvider() {
        return new Object[][]{
                { "0000000700Qw1/"},
                {".mypasswoRd1"},
        };
    }

    @DataProvider(name = "dataProviderToVerifyMessageWhenPasswordsNotMatch")
    private Object[][] dataProviderToVerifyMessageWhenPasswordsNotMatch() {
        return new Object[][]{
                {"1245367000Qw1/","14567000Qw1/"},
                {"mypas3swoRd1+","mypasswoRd1"},
        };
    }

    @Test(dataProvider = "dataProvider")
    public void changeCurrentPassword( String newPassword) throws IOException {
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        header.clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnChangePasswordButton()
                .enterOldPassword(provider.getPassword())
                .enterNewPassword(newPassword)
                .enterRepeatNewPassword(newPassword)
                .clickOnChangePasswordButton();
        header.clickUserMenu().clickSignOut();
        driver.navigate().refresh();
        HeaderSignedOutComponent headerOut = new HeaderSignedOutComponent(driver);
        String actual = headerOut.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(newPassword)
                .clickSignIn()
                .getTitleText();
        String expectedTitle = "Welcome to UBS! First choose location of waste disposal. Currently we work only in Kyiv and selected cities.";
        Assert.assertEquals(actual, expectedTitle);
        provider.setPassword(newPassword);
    }
   
    @Test
    public void verifyMessageWhenChangingPasswordToTheSame(){
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        String actual = header.clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnChangePasswordButton()
                .enterOldPassword(provider.getPassword())
                .enterNewPassword(provider.getPassword())
                .enterRepeatNewPassword(provider.getPassword())
                .getErrorMessageTheSamePassword();
        String expectedTitle = "Enter the new password!";
        Assert.assertEquals(actual, expectedTitle);
    }
   
    @Test(dataProvider = "dataProviderToVerifyMessageWhenPasswordsNotMatch")
    public void verifyMessageWhenPasswordsNotMatch(String newPassword,String repeatPassword){
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        String actual = header.clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnChangePasswordButton()
                .enterOldPassword(provider.getPassword())
                .enterNewPassword(newPassword)
                .enterRepeatNewPassword(repeatPassword)
                .getErrorMessageDontMatchPassword();
        String expectedTitle = "Passwords do not match!";
        Assert.assertEquals(actual, expectedTitle);
    }

}
