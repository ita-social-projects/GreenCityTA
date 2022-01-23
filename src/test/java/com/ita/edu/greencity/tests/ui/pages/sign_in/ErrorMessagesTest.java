package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.TestRun;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorMessagesTest extends TestRun {

    @DataProvider
    private Object[][] dataProvider() {
        return new Object[][] {
                {"1234"},
                {"testgreencity"},
                {"testgreencity@gmail"},
                {"testgreencity.com"}
        };
    }

    @Test
    public void emptyEmailTest() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        String expected = "Email is required";
        String actual = header.clickSignIn().inputEmail("").getErrorEmailMessage();

        Assert.assertEquals(actual, expected);
    }
}
