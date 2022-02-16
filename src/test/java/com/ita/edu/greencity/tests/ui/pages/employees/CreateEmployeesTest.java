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

    String CORRECT_MENU_NAME = "Leonard";
    String CORRECT_MENU_SURNAME = "Hofstadter";
    String CORRECT_MENU_PHONE = "676706767";
    String CORRECT_MENU_EMAIL = "test@ukr.net";


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
        System.out.println("00000");
        Employees employees = new Employees(driver);
        BasePage basePage = new BasePage(driver);
        basePage.sleep(6000);
        employees.pressButtonAddEmployee();
        employees.sendKeysNameArr(CORRECT_MENU_NAME);
        System.out.println("1111");
        employees.sendKeysSurnameArr(CORRECT_MENU_SURNAME);
        System.out.println("2222");
        employees.sendKeysPhoneArr(CORRECT_MENU_PHONE);
        System.out.println("3333");
        employees.sendKeysEmailArr(CORRECT_MENU_EMAIL);
        System.out.println("44444");
        basePage.sleep(2000);
        employees.pressButtonServiceManager();
        System.out.println("555555");
        employees.pressButtonSetectRegionAddMenu();
        System.out.println("66666");
        employees.pressButtonAddEmployeeAddMenu();


        System.out.println("correct add");
    }




}
