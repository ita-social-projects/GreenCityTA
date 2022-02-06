package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

    @Description("Verify correct redirection from pop-up to OderDetails page after pressing Continue button")
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

    @Description("Verify correct redirection from pop-up to HomePage page after pressing Back button")
    @Test
    public void checkRedirectionAfterBackButton() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnBackButton();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Description("Verify correct redirection from pop-up to HomePage page after pressing Close button")
    @Test
    public void checkRedirectionAfterCloseButton() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnCloseButton();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Description("Verify availability of correct options of dropdown menu and correctness of default value at pop-up")
    @Test
    public void checkCorrectDefaultDropdownValue() {
        SoftAssert softAssert = new SoftAssert();
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        SelectRegion selectRegion = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();
        softAssert.assertEquals(selectRegion.getListOfRegions().get(0).getText(), "Kyiv", "Incorrect default value of dropdown");
        softAssert.assertEquals(selectRegion.getListOfRegions().get(1).getText(), "Kyiv region", "Dropdown do not have 'Kyiv region' option");
        softAssert.assertAll();
    }

}
