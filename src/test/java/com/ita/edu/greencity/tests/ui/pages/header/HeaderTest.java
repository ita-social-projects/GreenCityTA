package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class HeaderTest extends TestRun {

    @Test
    public void ubsLogoButtonTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickUbsLogo();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
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
    public void greenCityRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickGreenCity();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
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
    public void userNameCorrectnessTest() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton();
        HeaderSignedInComponent header2 = new HeaderSignedInComponent(driver);
        String expected = provider.getUserName();
        String actual = header2.getUserName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void settingsButtonTest() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton();
        HeaderSignedInComponent header2 = new HeaderSignedInComponent(driver);
        header2.clickUserMenu().clickSettings();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/10485/edit";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void signOutButtonTest() {
        HeaderSignedOutComponent headerSignedOut = new HeaderSignedOutComponent(driver);
        headerSignedOut.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton();
        HeaderSignedInComponent headerSignedIn = new HeaderSignedInComponent(driver);
        headerSignedIn.clickUserMenu().clickSignOut();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }
}