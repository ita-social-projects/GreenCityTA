package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpErrorMessageTest extends TestRun {

    @Test
    public void signUpWithBlankConfirmPassword() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(RandomStringUtils.randomAlphabetic(5) + "@gmail.com")
                .inputUserNameIntoField(RandomStringUtils.randomAlphabetic(10))
                .inputPasswordIntoField(RandomStringUtils.randomAlphabetic(9))
                .clickOnShowHidePasswordButton()
                .inputConfirmPasswordIntoField("")
                .clickOnShowHideConfirmPasswordButton()
                .clickOnSignUpButton();
        String actualAlert = signUpComponent.getTextOfBlankConfirmPasswordFieldAlert();
        String expectedAlert = "Password is required";
        Assert.assertEquals(actualAlert, expectedAlert);
    }

    @Test
    public void signUpWithBlankFields() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField("")
                .inputUserNameIntoField("")
                .inputPasswordIntoField("")
                .inputConfirmPasswordIntoField("")
                .clickOnSignUpButton();
        String actualEmailAlert = signUpComponent.getTextOfBlankEmailFieldAlert();
        String actualUserNameAlert = signUpComponent.getTextOfBlankUserNameFieldAlert();
        String actualPasswordAlert = signUpComponent.getTextOfBlankPasswordFieldAlert();
        String actualConfirmPasswordAlert = signUpComponent.getTextOfBlankConfirmPasswordFieldAlert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailAlert, "Email is required","Problems with email error message:");
        softAssert.assertEquals(actualUserNameAlert, "User name is required","Problems with userName error message:");
        softAssert.assertEquals(actualPasswordAlert, "Password is required","Problems with password error message:");
        softAssert.assertEquals(actualConfirmPasswordAlert, "Password is required","Problems with confirm password error message:");
        softAssert.assertAll();
    }
}
