package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.LoginTestRun;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorMessagesTest extends LoginTestRun {

    @DataProvider
    private Object[][] emailDataProvider() {
        String expected = "Email is required";
        String expected1 = "Please check that your e-mail address is indicated correctly";

        return new Object[][] {
                {"", expected},
                {"1234", expected1},
                {"testgreencity", expected1},
                {"testgreencity.com", expected1},
                {"testgreencity323@gmail.com com", expected1}
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
                .inputEmail("")
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
