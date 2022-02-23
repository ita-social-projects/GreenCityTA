package com.ita.edu.greencity.tests.api;

import com.ita.edu.greencity.tests.ui.utils.RetryAnalyzer;
import com.ita.edu.greencity.utils.ValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class ApiTestRunner {
    protected static ValueProvider provider;

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext iTestContext) {
        for (ITestNGMethod method : iTestContext.getAllTestMethods()) {
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        if (provider == null) {
            provider = new ValueProvider();
        }
    }
}
