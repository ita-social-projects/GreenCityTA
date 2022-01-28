package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRunnerInitDriverWithBeforeClass;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorMessagesTest extends TestRunnerInitDriverWithBeforeClass {

    @BeforeMethod
    public void beforeMethod() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn();
    }

    @AfterMethod
    public void afterMethod() {
        SignInComponent signin = new SignInComponent(driver);
        signin.clickCloseBtn();
    }

    @DataProvider
    private Object[][] emailDataProvider() {
        final String expected = "Email is required";
        final String expectedIncorrect = "Please check that your e-mail address is indicated correctly";

        return new Object[][] {
                {"", expected},
                {"1234", expectedIncorrect},
                {"testgreencity", expectedIncorrect},
                {"testgreencity.com", expectedIncorrect},
                {"testgreencity323@gmail.com com", expectedIncorrect}
        };
    }

    @DataProvider
    private Object[][] generalDataProvider() {
        return new Object[][] {
                {provider.getEmail(), "invalid"},
                {provider.getEmail(), "123"},
                {"email@gmail.com", provider.getPassword()},
        };
    }

    @Test(dataProvider = "emailDataProvider")
    public void emailErrorTest(String email, String expected) {
        SignInComponent signin = new SignInComponent(driver);

        String actual = signin
                .inputEmail(email)
                .unfocus()
                .getErrorEmailMessage();

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void passwordErrorTest() {
        SignInComponent signin = new SignInComponent(driver);
        String expected = "Password is required";

        String actual = signin
                .inputEmail(provider.getEmail())
                .inputPassword("")
                .unfocus()
                .getErrorPasswordMessage();

        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "generalDataProvider")
    public void generalErrorTest(String email, String password) {
        SignInComponent signin = new SignInComponent(driver);
        String expected = "Bad email or password";

        signin.inputEmail(email).inputPassword(password).clickSignIn();
        String actual = signin.getErrorGeneralMessage();

        Assert.assertEquals(actual, expected);
    }

}