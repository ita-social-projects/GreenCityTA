package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloseSignUpPopUpTest extends TestRun {
    @Test
    public void checkEngLocalSignUpTitle() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        String expected = "UBS Courier";
        UbsHomePage signUpComponent = new SignUpComponent(driver).clickOnExitButton();
        String actual = signUpComponent.getTitleH1Text();
        Assert.assertEquals(actual, expected);
    }
}
