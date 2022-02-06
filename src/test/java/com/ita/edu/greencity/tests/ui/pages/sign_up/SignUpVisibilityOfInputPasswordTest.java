package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpVisibilityOfInputPasswordTest extends TestRun {

    @Test
    @Description("Check the visibility of passwords after clicking on ShowHideButton near fields")
    @Issue("29")
    @Severity(SeverityLevel.NORMAL)
    public void checkVisibilityOfInputPasswordsFields() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        String password = "tesT_123";
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(RandomStringUtils.randomAlphabetic(5) + "@gmail.com")
                .inputUserNameIntoField(RandomStringUtils.randomAlphabetic(10))
                .inputPasswordIntoField(password)
                .clickOnShowHidePasswordButton()
                .inputConfirmPasswordIntoField(password)
                .clickOnShowHideConfirmPasswordButton();
        boolean passwordIsVisible = signUpComponent.checkPasswordIsVisible();
        boolean confirmPasswordIsVisible = signUpComponent.checkConfirmPasswordIsVisible();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(passwordIsVisible, "Password field haven't become visible!");
        softAssert.assertTrue(confirmPasswordIsVisible, "Confirm password field haven't become visible!");
        softAssert.assertAll();
    }
}
