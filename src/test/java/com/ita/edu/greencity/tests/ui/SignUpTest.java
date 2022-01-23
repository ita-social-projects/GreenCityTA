package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.header.SignUpComponent;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends TestRun {

    @Test
    public void checkEngLocalSignUpTitle() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        String expected = "Hello!\n" +
                "Please enter your details to sign up";
        String actual = new SignUpComponent(driver).getTextOfTitle();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void signUpWithBlankConfirmPassword() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(RandomStringUtils.randomAlphabetic(5) + "@gmail.com")
                .inputUserNameIntoField(RandomStringUtils.randomAlphabetic(10))
                .inputPasswordIntoField(RandomStringUtils.randomAlphabetic(9))
                .inputConfirmPasswordIntoField("")
                .clickOnSignUpButton();
        String actualAlert = signUpComponent.getTextOfBlankConfirmPasswordFieldAlert();
        String expectedAlert = "Password is required";
        Assert.assertEquals(actualAlert, expectedAlert);
    }
}
