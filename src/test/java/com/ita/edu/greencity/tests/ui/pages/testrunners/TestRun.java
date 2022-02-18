package com.ita.edu.greencity.tests.ui.pages.testrunners;


import com.ita.edu.greencity.tests.ui.utils.RetryAnalyzer;
import com.ita.edu.greencity.tests.ui.utils.TestNGListener;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

@Listeners(TestNGListener.class)
public class TestRun {
    protected static ValueProvider provider;
    protected WebDriver driver;

    @BeforeSuite(description = "Make chromedriver setup")
    public void beforeSuite(ITestContext iTestContext) throws IOException {
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        WebDriverManager.chromedriver().setup();
        provider = new ValueProvider();
    }

    @BeforeMethod(description = "Configure chromedriver and go to UbsHomePageURL")
<<<<<<< HEAD
    public void beforeMethod(ITestContext iTestContext){
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--allow-failed-policy-fetch-for-test");
        options.addArguments("--disable-browser-side-navigation");
      //  options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--window-size=1920,1080", "--no-sandbox", "'--disable-dev-shm-usage");
        options.addArguments("--disable-web-security");
       // options.addArguments("--user-data-dir");
        options.addArguments("--allow-running-insecure-content");
        driver = new ChromeDriver(options);
=======
    public void beforeMethod(ITestContext iTestContext) {
        driver = new ChromeDriver();
>>>>>>> b40bf5fa8837a2b30753eec9d5622d1ed10d085b
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(provider.getUbsHomePageURL());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("language", "en");
        driver.navigate().refresh();
        iTestContext.setAttribute("driver", driver);
    }

    @AfterMethod(description = "Quite chromedriver")
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }

    }


}