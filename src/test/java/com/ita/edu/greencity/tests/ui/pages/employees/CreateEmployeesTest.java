package com.ita.edu.greencity.tests.ui.pages.employees;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEmployeesTest extends EmployeesTestRun {
    String CORRECT_MENU_NAME = "Leonard";
    String CORRECT_MENU_SURNAME = "Hofstadter";
    String CORRECT_MENU_PHONE = "676706767";
    String CORRECT_MENU_EMAIL = "test@ukr.net";



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
        System.out.println("6666");
        employees.sendKeysSurnameArr(CORRECT_MENU_SURNAME);
        System.out.println("7777");
        employees.sendKeysPhoneArr(CORRECT_MENU_PHONE);
        System.out.println("8888");
        employees.sendKeysEmailArr(CORRECT_MENU_EMAIL);
        System.out.println("9999");
        basePage.sleep(9000);
        employees.pressButtonServiceManager();
        System.out.println("----");
        employees.pressButtonSetectRegionAddMenu();
        System.out.println("====");
        employees.pressButtonAddEmployeeAddMenu();
        String xpath = String.format("//span[contains(text(), '%s')]", CORRECT_MENU_NAME);
        String actual = driver.findElement(By.xpath(xpath)).getText();
        Assert.assertEquals(actual, CORRECT_MENU_NAME);

        employees.locatorOfName("Harry");
        System.out.println("correct add");
    }




}
