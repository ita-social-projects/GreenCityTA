package com.ita.edu.greencity.tests.ui.pages.employees;

import com.ita.edu.greencity.tests.ui.utils.RetryAnalyzer;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.employees.Employees;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class CreateEmployeesTest {
    String CORRECT_MENU_NAME = "Leonard";
    String CORRECT_MENU_SURNAME = "Hofstadter";
    String CORRECT_MENU_PHONE = "676706767";
    String CORRECT_MENU_EMAIL = "test@ukr.net";

    protected static ValueProvider provider;
    protected WebDriver driver;
    @BeforeClass(description = "Make chromedriver setup")
    public void beforeSuite(ITestContext iTestContext) throws IOException {
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        WebDriverManager.chromedriver().setup();
        provider = new ValueProvider();
        System.out.println("0000");
    }



    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        String xpathLoaderEmployeesPage = "//mat-spinner[@role = 'progressbar']";
        BasePage basePage = new BasePage(driver);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(provider.getUbsHomePageURL());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("language", "en");
        driver.navigate().refresh();
        iTestContext.setAttribute("driver", driver);
        basePage.sleep(1000);
        System.out.println("1111");

        SignInComponent signInComponent = new SignInComponent(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        Employees employees = new Employees(driver);
        ubsHomePage.clickSingInButton();
        signInComponent.inputEmail(provider.getEmailAdmin()).inputPassword(provider.getPasswordAdmin());
//        employees.clickSingInButton();
        System.out.println("2222");
        basePage.sleep(12000);

        employees.clickAdminPopMenu();
        basePage.sleep(5000);
        System.out.println("3333");
        employees.clickButtonUbsAdmin();
        basePage.sleep(5000);
        System.out.println("4444");
        employees.clickEmployyesButton();
        employees.loadData(xpathLoaderEmployeesPage);
        System.out.println("5555");
    }


    @AfterMethod
    public void afterMethod(){
//        if (driver != null) {
//            driver.quit();
//        }
    }
    //
    @AfterClass
    public void tearDown(){
//        if (driver != null) {
//            driver.quit();
//        }
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
