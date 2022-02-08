package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class HeaderTest extends TestRun {

    @Test
    @Description("Test redirection to UBS site by clicking on logo")
    @Issue("96")
    public void ubsLogoButtonTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickUbsLogo();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test redirection to site by clicking on Sorting Rules button")
    @Issue("96")
    public void sortingRulesRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickSortingRules();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String expected = "https://nowaste.com.ua/yak-sortyvaty-na-karantuni/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test redirection to site by clicking on EcoShop button")
    @Issue("96")
    public void ecoShopRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickEcoShop();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String expected = "https://shop.nowaste.com.ua/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test redirection to site by clicking on GreenCity button")
    @Issue("96")
    public void greenCityRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickGreenCity();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test to check switching btw languages and changing language at LocalStorage")
    @Issue("96")
    public void languageSwitcherTest() {
        HeaderComponent header = new HeaderComponent(driver);
        String expectedUA = "ua";
        String expectedEN = "en";
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        SoftAssert softAssert = new SoftAssert();

        header.clickLanguageSwitcher().languageChoose("ua");
        String actual = localStorage.getItem("language");
        softAssert.assertEquals(actual, expectedUA);

        header.clickLanguageSwitcher().languageChoose("en");
        actual = localStorage.getItem("language");
        softAssert.assertEquals(actual, expectedEN);

        softAssert.assertAll();
    }

    @Test
    @Description("Test if username displayed correctly")
    @Issue("96")
    public void userNameCorrectnessTest() {
        HeaderSignedOutComponent headerSignedOut = new HeaderSignedOutComponent(driver);
        headerSignedOut.clickSignIn().inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn().clickOnContinueButton();
        HeaderSignedInComponent headerSignedIn = new HeaderSignedInComponent(driver);
        String expected = provider.getUserName();
        String actual = headerSignedIn.getUserName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test if Settings button redirects to correct page")
    @Issue("96")
    public void settingsButtonTest() {
        HeaderSignedOutComponent headerSignedOut = new HeaderSignedOutComponent(driver);
        headerSignedOut.clickSignIn().inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn().clickOnContinueButton();
        HeaderSignedInComponent headerSignedIn = new HeaderSignedInComponent(driver);
        headerSignedIn.clickUserMenu().clickSettings();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/10485/edit";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test if after clicking on SignOut button there is redirection to main page")
    @Issue("96")
    public void signOutButtonTest() {
        HeaderSignedOutComponent headerSignedOut = new HeaderSignedOutComponent(driver);
        headerSignedOut.clickSignIn().inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn().clickOnContinueButton();
        HeaderSignedInComponent headerSignedIn = new HeaderSignedInComponent(driver);
        headerSignedIn.clickUserMenu().clickSignOut();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }
}