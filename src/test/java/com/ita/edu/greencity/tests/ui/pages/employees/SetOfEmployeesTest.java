package com.ita.edu.greencity.tests.ui.pages.employees;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetOfEmployeesTest extends EmployeesTestRun {

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
        Assert.assertTrue(employees.checkIfButtonAddEmplOnDipl());
        System.out.println("Add button on display");

    }

    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void checkEditEmployeeButton() {
        String name = "Harry";
        Employees employees = new Employees(driver);
        employees.locatorOfName(name);
        employees.checkLocatorOfEditEmployeeButton();
        employees.pressCancelButton();
    }


    @Test
    @Description("")
    @Issue("")
    @Severity(SeverityLevel.TRIVIAL)
    public void removeEmployee() {
        String name = "Leonardddd";
        Employees employees = new Employees(driver);
        employees.locatorOfName(name);
        employees.pressLocatorRemove();
        employees.pressLocatorReaskRemoveButton();
        String xpath = String.format("//span[contains(text(), '%s')]", name);
        WebElement actual = driver.findElement(By.xpath(xpath));
        Assert.assertFalse(actual.isDisplayed());

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
        employees.sendKeysPhoneArr("676706767");
        System.out.println("Input in phone");
    }


}
