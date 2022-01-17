package com.ita.edu.greencity.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends TestRun {
    @Test
    public void firstTest() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/";
        String actual = driver.getCurrentUrl();

        Assert.assertEquals(actual, expected);
    }
}
