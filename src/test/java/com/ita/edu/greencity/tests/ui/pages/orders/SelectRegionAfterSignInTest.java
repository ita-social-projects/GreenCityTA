package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectRegionAfterSignInTest extends TestRun {
    public final static String region = "Kyiv";

    @Description("Verify equality of regions from pop-up and OrderDetailsPage")
    @Test
    public void changeRegionAfterSignIn() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        String actual = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue(region)
                .clickOnContinueButton().getLocationFromTitle().substring(11).trim();
        Assert.assertEquals(actual, region);
    }

    @Test
    public void checkRedirectionAfterContinueButton() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs/order";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

}
