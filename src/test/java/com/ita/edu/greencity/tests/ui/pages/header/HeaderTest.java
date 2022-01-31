package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Locale;

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
        String actual = Locale.getDefault().getLanguage();
        if (header.getLanguage() == "En") {
            header.clickLanguageSwitcher().languageChoose("ua");
            Assert.assertEquals(actual, expectedUA);
        } else {
            header.clickLanguageSwitcher().languageChoose("en");
            Assert.assertEquals(actual, expectedEN);
        }
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
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton();
        HeaderSignedInComponent header2 = new HeaderSignedInComponent(driver);
        header2.clickUserMenu().clickSignOut();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }
}