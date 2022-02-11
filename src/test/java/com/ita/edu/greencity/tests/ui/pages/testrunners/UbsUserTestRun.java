package com.ita.edu.greencity.tests.ui.pages.testrunners;

import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class UbsUserTestRun extends TestRun {

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail("greencitytest62@gmail.com")
                .inputPassword("123456Qw/")
                .clickSignIn()
                .clickOnContinueButton();

        new HeaderSignedInComponent(driver).clickUserMenu()
                .clickUbsUser();
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
    }
}
