package com.ita.edu.greencity.tests.ui.pages.ubs_homepage;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BeforeUbsHomePageTestRun extends TestRun {

    @BeforeClass
    public void beforeClass() {
        super.beforeMethod();
    }

    @BeforeMethod
    public void beforeMethod() {
        SignInComponent signInComponent = new SignInComponent(driver);
        BasePage basePage = new BasePage(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        ubsHomePage.clickSingInButton();
        signInComponent.inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn();
        basePage.implicitWait(5000);
        selectRegion.clickOnCloseButton();
    }

    @AfterMethod
    public void afterMethod(){

    }

    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
