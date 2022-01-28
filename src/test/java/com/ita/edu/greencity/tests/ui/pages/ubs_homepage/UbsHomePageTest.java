
package com.ita.edu.greencity.tests.ui.pages.ubs_homepage;


import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;

import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UbsHomePageTest extends TestRun {


    @BeforeMethod
    public void befMet(){

        BasePage basePage = new BasePage(driver);
        HeaderComponent headerComponent = new HeaderComponent(driver);
        SignInComponent signInComponent = new SignInComponent(driver);
        basePage.implicitWait(1000);

        driver.findElement(By.className("ubs-header-sign-in")).click();
        signInComponent.inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn();
        basePage.implicitWait(5000);
        WebElement butNext = driver.findElement(By.className("close-btn"));
        butNext.click();
    }

    @Test
    public void checkUbsCurrentsUrl() {

        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void ubs2Test() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "UBS Courier";
        ubsHomePage.getTextHeaderMainText();
        Assert.assertEquals(ubsHomePage.getTextHeaderMainText(), expected);
    }

    @Test
    public void getSection4MiddleText() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "We are accepting Fabric wastes (damaged clothes) for the utilization in a separate package of 120L size or 120L size";
        ubsHomePage.getSectionFirstMiddleText();
        Assert.assertEquals(ubsHomePage.getSectionFirstMiddleText(), expected);
    }

    @Test
    public void checkOrderCurrentUrl() {
        driver.get(provider.getUbsHomePageURL());
        BasePage basePage = new BasePage(driver);
        WebElement button = driver.findElement(By.xpath("//div[@class='ubsHomepage']//*/button[@class='button primary-global-button']"));
        button.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        basePage.implicitWait(4000);
        WebElement button2 = driver.findElement(By.xpath("//button[@class='btn primaryButton primary-global-button']"));
        button2.click();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs/order";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void checkIfTextEquals(){
        BasePage basePage = new BasePage(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "The UBS Courier service will save your time and money for getting to the sorting station. And as a bonus, you will receive a free delivery of our eco-products around Kyiv.";
        Assert.assertEquals(ubsHomePage.getMiddleText(), expected);
    }

    @Test
    public void checkIfTextnextEquals(){
        BasePage basePage = new BasePage(driver);
        SelectRegion selectRegion = new SelectRegion(driver);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.clickHeaderButtonOrderCourier();
        basePage.implicitWait(1000);
        selectRegion.chooseRegionByIndex(1);
        selectRegion.clickOnContinueButton();

        String exp = "https://ita-social-projects.github.io/GreenCityClient/#/ubs/order";
        String act = driver.getCurrentUrl();
        Assert.assertEquals(act,exp);
    }


}