package com.ita.edu.greencity.tests.ui.pages.employees;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateEmployeesTest extends EmployeesTestRun {
    @BeforeMethod
    public void beforeMethod(ITestContext testContext)   {
        super.beforeMethod(testContext);
        SignInComponent signInComponent = new SignInComponent(driver);
        BasePage basePage = new BasePage(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        Employees employees = new Employees(driver);
        basePage.sleep(2000);
        
    }


    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkCorrectAddEmployee() {
        Employees employees = new Employees(driver);
        BasePage basePage = new BasePage(driver);
        basePage.sleep(5000);
        employees.pressButtonAddEmployee();
        employees.sendKeysNameArr("Leonard");
        employees.sendKeysSurnameArr("Hofstadter");
        employees.sendKeysPhoneArr("676706767");
        employees.sendKeysEmailArr("test@ukr.net");
        basePage.sleep(2000);
        employees.pressButtonServiceManager();
        employees.pressButtonSetectRegionAddMenu();
        employees.pressButtonAddEmployeeAddMenu();


        System.out.println("correct add");
    }




}
