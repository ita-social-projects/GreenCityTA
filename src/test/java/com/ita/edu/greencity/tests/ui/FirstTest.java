package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends TestRun {
    @Test
    public void firstTest() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

}
