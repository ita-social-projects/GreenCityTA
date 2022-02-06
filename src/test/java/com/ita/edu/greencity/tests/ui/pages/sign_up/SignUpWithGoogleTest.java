package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.google_account.GoogleSignInInputEmailPopUp;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsUsersService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SignUpWithGoogleTest extends TestRun {

    EcoNewsUsersService ecoNewsUsersService = new EcoNewsUsersService();
    private final String userEmail = "registertesttest88@gmail.com";

    @BeforeTest(description = "Delete user by email if it exists in database before registration")
    public void checkRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }

    @Test
    @Description("Check correct registration of user with Google")
    @Issue("29")
    @Severity(SeverityLevel.CRITICAL)
    public void test() {
        SignUpComponent signUpComponent = new HeaderSignedOutComponent(driver).clickSignUp();
        String userPassword = "Tetsregistr_1";
        signUpComponent.clickOnSignUpWithGoogleButton();
        new GoogleSignInInputEmailPopUp(driver).inputEmailIntoField(userEmail)
                .clickOnContinueButton()
                .inputPasswordIntoField(userPassword)
                .clickOnContinueButton();
        new SelectRegion(driver).clickOnCloseButton();
        signUpComponent.clickOnExitButton();
        String actualUserName = new HeaderSignedInComponent(driver).getUserName();
        Assert.assertEquals(actualUserName,"Tetsregistr Tetsregistr");
    }

    @AfterTest(description = "Delete user by email in database after registration")
    public void deleteRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }
}
