package com.ita.edu.greencity.ui.pages.employees;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class EmployeesTestRun extends TestRun {
//    @BeforeClass
//    public void beforeClass(ITestContext testContext) {
//
//        super.beforeMethod(testContext);
//    }
//
    @BeforeMethod
    public void beforeMethod(ITestContext testContext) {
        super.beforeMethod(testContext);
        SignInComponent signInComponent = new SignInComponent(driver);
        BasePage basePage = new BasePage(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        Employees employees = new Employees(driver);
        ubsHomePage.clickSingInButton();
        signInComponent.inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn();
        basePage.implicitWait(5000);
        selectRegion.clickOnCloseButton();
        employees.clickAdminPopMenu();

    }

    @AfterMethod
    public void afterMethod(){

        if (driver != null) {
            driver.quit();
        }
    }
//
    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

}
