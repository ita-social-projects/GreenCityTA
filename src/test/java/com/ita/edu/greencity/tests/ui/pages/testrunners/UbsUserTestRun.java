package com.ita.edu.greencity.tests.ui.pages.testrunners;

import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class UbsUserTestRun extends TestRun {

    @BeforeClass
    public void beforeClass() {
        super.beforeMethod();
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail("grabarskiy02@gmail.com")
                .inputPassword("123456Qw/")
                .clickSignIn()
                .clickOnContinueButton();

        new HeaderSignedInComponent(driver).clickUserMenu()
                .clickUbsUser();
    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {

    }

    @AfterClass
    public void afterClass() {
        super.afterMethod();
    }
}
