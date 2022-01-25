package com.ita.edu.greencity.tests.ui;


import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;
import java.time.Duration;

public class TestRun {
    protected WebDriver driver;
    protected static ValueProvider provider;

    @BeforeSuite
    public void beforeSuite() throws IOException {
        WebDriverManager.chromedriver().setup();
        provider = new ValueProvider();
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(provider.getUbsHomePageURL());
        LocalStorage localStorage = ((WebStorage)driver).getLocalStorage();
        localStorage.setItem("language", "en");
        driver.navigate().refresh();
    }

    @AfterMethod
    public void afterMethod(){
        if (driver != null) {
            driver.quit();
        }
    }


}