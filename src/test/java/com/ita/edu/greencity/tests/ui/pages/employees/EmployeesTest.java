package com.ita.edu.greencity.tests.ui.pages.employees;


import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeesTest extends TestRun {

    @BeforeMethod
    public void beforeMethod(ITestContext testContext)  {
        super.beforeMethod(testContext);
        SignInComponent signInComponent = new SignInComponent(driver);
        BasePage basePage = new BasePage(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        Employees employees = new Employees(driver);
        ubsHomePage.clickSingInButton();
        signInComponent.inputEmail(provider.getEmailAdmin()).inputPassword(provider.getPasswordAdmin()).clickSignIn();
        selectRegion.clickOnCloseButton();
        employees.clickAdminPopMenu();
        employees.clickButtonUbsAdmin();
        employees.clickEmployyesButton();
    }


    @Test
    @Description("Check if current url")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkIfCurrentUrl() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs-admin/employee/1";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
        System.out.println("url ok");
    }

    @Test
    @Description("Check if Add Employees button on display")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkIfAddEmployeesButtonOnDisplay() {
        Employees employees = new Employees(driver);
        BasePage basePage = new BasePage(driver);
        String xpath = "/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/div/mat-drawer-container/mat-drawer-content/div/app-ubs-admin-employee/div/div[1]/div[2]/button";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(employees.checkIfButtonAddEmplOnDipl());
        System.out.println("Add button on display");

    }


    @Test
    @Description("Check if Add Employees button on display")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void addNewUser() {
        Employees employees = new Employees(driver);
//        employees.pressButtonAddEmployee();
        Assert.assertTrue(employees.checkIfInputOnDisplay());
        System.out.println("Input on display");
    }

    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void writeTextInInputName() {
        Employees employees = new Employees(driver);
        employees.pressButtonAddEmployee();
        employees.sendKeysNameArr("Leonard");
        System.out.println("Input in nameArr");
    }

    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void writeTextInInputSurname() {
        Employees employees = new Employees(driver);
        employees.pressButtonAddEmployee();
        employees.sendKeysSurnameArr("Hofstadter");
        System.out.println("Input in surnameArr");
    }

    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void writeTextInInputPhone() {
        Employees employees = new Employees(driver);
        employees.pressButtonAddEmployee();
        employees.sendKeysPhoneArr("12121212");
        System.out.println("Input in surnameArr");
    }

    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void writeTextInAddUserMenu() {
        Employees employees = new Employees(driver);
        employees.pressButtonAddEmployee();
        employees.sendKeysNameArr("Leonard");
        employees.sendKeysSurnameArr("Hofstadter");

        System.out.println("Input is correct");
    }


}
