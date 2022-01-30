package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRunnerInitDriverWithBeforeClass;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestRunnerInitDriverWithBeforeClass {
    @BeforeMethod
    public void beforeMethod() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn();
    }

    @Test
    public void loginTest() {
        SignInComponent signin = new SignInComponent(driver);
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);

        String expected = provider.getUserName();
        signin
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnCloseButton();

        String actual = header.getUserName();

        Assert.assertEquals(actual, expected);
    }
}
