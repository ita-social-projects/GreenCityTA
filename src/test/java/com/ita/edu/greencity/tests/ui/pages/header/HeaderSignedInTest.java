package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HeaderSignedInTest extends TestRun {

    @BeforeMethod
    public void loginToUBS() {
        HeaderSignedOutComponent headerSignedOut = new HeaderSignedOutComponent(driver);
        headerSignedOut.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();
    }

    @Test
    @Description("Test if username displayed correctly")
    @Issue("96")
    public void userNameCorrectnessTest() {
        String expected = provider.getUserName();
        String actual = new HeaderSignedInComponent(driver).getUserName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test if Settings button redirects to correct page")
    @Issue("96")
    public void settingsButtonTest() {
        new HeaderSignedInComponent(driver).clickUserMenu().clickSettings();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/29/edit";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test if after clicking on SignOut button there is redirection to main page")
    @Issue("96")
    public void signOutButtonTest() {
        new HeaderSignedInComponent(driver).clickUserMenu().clickSignOut();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }
}
