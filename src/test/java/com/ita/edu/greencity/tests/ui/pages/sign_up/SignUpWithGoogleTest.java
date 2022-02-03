package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.google_account.GoogleSignInInputEmailPopUp;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;

import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsUsersService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SignUpWithGoogleTest extends TestRun {

    EcoNewsUsersService ecoNewsUsersService = new EcoNewsUsersService();
    private final String userEmail = "registertesttest88@gmail.com";

    @BeforeTest
    public void checkRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }

    @Test
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

    @AfterTest
    public void deleteRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }
}
