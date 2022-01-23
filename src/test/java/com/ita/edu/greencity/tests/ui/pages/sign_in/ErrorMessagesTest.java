package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.TestRun;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorMessagesTest extends TestRun {
    BasePage basePage = new BasePage(driver);
    HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);

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
        String expected = "Email is required";
        header.clickSignIn();
    }
}
