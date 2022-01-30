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
        softAssert.assertEquals(actualEmailAlert, "Email is required", "Problems with email error message:");
        softAssert.assertEquals(actualUserNameAlert, "User name is required", "Problems with userName error message:");
        softAssert.assertEquals(actualPasswordAlert, "Password is required", "Problems with password error message:");
        softAssert.assertEquals(actualConfirmPasswordAlert, "Password is required", "Problems with confirm password error message:");
        softAssert.assertAll();
    }

    @Test
    public void signUpWithDifferentPasswords() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(RandomStringUtils.randomAlphabetic(5) + "@gmail.com")
                .inputUserNameIntoField(RandomStringUtils.randomAlphabetic(10))
                .inputPasswordIntoField("tesT_123")
                .inputConfirmPasswordIntoField("test")
                .clickOnSignUpButton();
        String actualAlert = signUpComponent.getTextOfBlankConfirmPasswordFieldAlert();
        Assert.assertEquals(actualAlert, "Passwords do not match");
    }

    @Test
    public void signUpWithInvalidFields() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        String password = RandomStringUtils.randomAlphabetic(9);
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(RandomStringUtils.randomAlphabetic(4))
                .inputUserNameIntoField("." + RandomStringUtils.randomAlphabetic(4))
                .inputPasswordIntoField(password)
                .inputConfirmPasswordIntoField(password)
                .clickOnSignUpButton();
        String actualEmailAlert = signUpComponent.getTextOfBlankEmailFieldAlert();
        String actualUserNameAlert = signUpComponent.getTextOfBlankUserNameFieldAlert();
        String actualPasswordAlert = signUpComponent.getTextOfBlankPasswordFieldAlert();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmailAlert, "Please check that your e-mail address is indicated correctly", "Problems with email error message:");
        softAssert.assertEquals(actualUserNameAlert, "The name must contain 6-30 characters and can contain letters(a-z), numbers(0-9) and a dot(.), dot at the start, at the end and consecutive dot is forbidden", "Problems with userName error message:");
        softAssert.assertEquals(actualPasswordAlert, "Password has contain at least one character of Uppercase letter (A-Z), Lowercase letter (a-z), Digit (0-9), Special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)", "Problems with password error message:");
        softAssert.assertAll();
    }

    @Test
    public void signUpWithTooShortPassword() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        String password = RandomStringUtils.randomAlphabetic(4);
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(RandomStringUtils.randomAlphabetic(5) + "@gmail.com")
                .inputUserNameIntoField(RandomStringUtils.randomAlphabetic(10))
                .inputPasswordIntoField(password)
                .inputConfirmPasswordIntoField(password)
                .clickOnSignUpButton();
        String actualAlert = signUpComponent.getTextOfBlankPasswordFieldAlert();
        Assert.assertEquals(actualAlert, "Password must be at least 8 characters long");
    }
}
