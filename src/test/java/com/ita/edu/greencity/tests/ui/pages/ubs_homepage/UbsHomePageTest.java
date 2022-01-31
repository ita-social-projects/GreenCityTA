
package com.ita.edu.greencity.tests.ui.pages.ubs_homepage;
import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.*;



public class UbsHomePageTest extends com.ita.edu.greencity.tests.ui.pages.ubs_homepage.BeforeUbsHomePageTestRun {


    @Test
    public void checkUbsCurrentsUrl() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void checkImgArmoredTrackOnDisplay() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        BasePage basePage = new BasePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertTrue(ubsHomePage.checkImgArmoredTrackOnDisplay());
    }

    @Test
    public void checkIfUbsTextEqualsEnLang() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "UBS Courier";
        Assert.assertEquals(ubsHomePage.getTextHeaderMainText(), expected);
    }

    @Test
    public void checkErrorIfUbsTextEqualsEnLang() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "wrong!!!";
        Assert.assertNotEquals(ubsHomePage.getTextHeaderMainText(), expected);
    }

    @Test
    public void checkStyleOfElement() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "Lato, sans-serifsdfsdfsd";
        Assert.assertNotEquals(ubsHomePage.getHeaderText(), expected);
    }

    @Test
    public void getSection4MiddleTextEnLang() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "We are accepting Fabric wastes (damaged clothes) for the utilization in a separate package of 120L size or 120L size";
        Assert.assertEquals(ubsHomePage.getSectionFirstMiddleText(), expected);
    }

    @Test
    public void checkOrderCurrentUrl() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver)
                .clickHeaderButtonOrderCourier();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        SelectRegion selectRegion = new SelectRegion(driver);
        selectRegion.clickOnContinueButton();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs/order";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void checkIfTextEquals(){
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "The UBS Courier service will save your time and money for getting to the sorting station. And as a bonus, you will receive a free delivery of our eco-products around Kyiv.";
        Assert.assertEquals(ubsHomePage.getMiddleText(), expected);
    }

    @Test
    public void checkIfDivHowToPrepareOnDisplay(){
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        Assert.assertTrue(ubsHomePage.checkIfDivHowToPrepare());
    }

    @Test
    public void checkLengthListHowToPrepare() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        BasePage basePage = new BasePage(driver);
        basePage.implicitWait(5000);
        int expected = 7;
        List<WebElement> UlElement = driver.findElements(By.xpath("//section[1]//*/div[@class='header-right']/ul[1]//*"));
        List<WebElement> actual = new ArrayList<>();
        actual.addAll(UlElement);
//        UlElement.forEach(i->actual.add(i));
        System.out.println(actual.size());
        Assert.assertEquals(actual.size(),expected);
    }

    @Test
    public void checkElementsInListHowToPrepare() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        BasePage basePage = new BasePage(driver);
        basePage.implicitWait(5000);
        List <String> expected = Arrays.asList(
                ("check out our Rules of sorting wastes (link)."),
                ("Everything you give to the Courier must be clean and dry – it’s Must Have."),
                ("Recyclables and non-recyclable plastics can be put together in one package /sack of a 120 liters size - we will re-sort everything."),
                ("We are accepting Fabric wastes (damaged clothes) for the utilization in a separate package of 120L size or 120L size"),
                ("1 package/sack should be up to 30 kg."),
                ("If you don't have a package/sack of the necessary size you can get it right from the Courier or order a whole roll of packages in our store and pack the wastes beforehand so you will save a big amount of time."),
                ("The courier picks up at least 2 packages within Kyiv. Driving a long distance to take only 1 package is not environmentally friendly, and it significantly increases the waiting time for each customer. If you live within a radius of 20 km from Kyiv - the Courier takes out 20 packages of 120 liters.")
        );
        List<WebElement> UlElement = driver.findElements(By.xpath("//section[1]//*/div[@class='header-right']/ul[1]//*"));
        List<String> actual = new ArrayList<>();
        UlElement.forEach(i->actual.add(i.getText()));

        System.out.println(actual.size());
        System.out.println(expected.size());
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void checkButton() {
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        Assert.assertTrue(ubsHomePage.checkIfButtonOnDisplay());
    }

    @Test
    public void checkTextPriceUbsCourier(){
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        String expected = "The cost of the service “UBS Courier";
        Assert.assertEquals(ubsHomePage.checkTextPriceUbsCourier(), expected);
    }
}

