package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.ui.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsCertificateService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderDetailsPageCertificateTest extends TestRun {
    EcoNewsCertificateService ecoNewsCertificateService = new EcoNewsCertificateService();

    private final String codeValueActive = TestHelpersUtils.generateRandomCertificateNumber();
    private final String statusValueActive = "ACTIVE";
    private final String expiration_dateValue = "2022-11-11 00:00:00";
    private final int pointsValue = 500;


    private String nonExistCertificate() {
        String value = TestHelpersUtils.generateRandomCertificateNumber();
        for (; ecoNewsCertificateService.checkIfCertificateExists(value); ) {
            value = TestHelpersUtils.generateRandomOrderNumber();
        }
        return value;
    }

    @DataProvider
    private Object[][] certificateDataProvider() {
        final String codeValueActive = this.codeValueActive;
        final String codeValueUsed = ecoNewsCertificateService.selectRandomUsedCertificate();
        final String codeValueNotExist = nonExistCertificate();
        return new Object[][]{
                {"Certificate for 500 UAH activated", codeValueActive},
                {"Certificate has already been used", codeValueUsed},
                {"Certificate not accepted, please try again", codeValueNotExist},
        };
    }
        @DataProvider
        private Object[][] certificateButtonProvider () {
            final String randomCertificate = TestHelpersUtils.generateRandomCertificateNumber();
            return new Object[][]{
                    {false, ""},
                    {true, randomCertificate},
            };
        }
        @BeforeTest
        public void AddCertificate () throws Exception {
            ecoNewsCertificateService.deleteCertificateByCode(codeValueActive);
            ecoNewsCertificateService.addCertificate(codeValueActive, statusValueActive, expiration_dateValue, pointsValue);
        }
        @BeforeMethod
        public void preConditions () {
            HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
            header.clickSignIn()
                    .inputEmail(provider.getEmail())
                    .inputPassword(provider.getPassword())
                    .clickSignIn()
                    .chooseRegionByIndex(0)
                    .clickOnContinueButton();

        }
        @Description("Checks coupon alert")
        @Issue("90")
        @Test(dataProvider = "certificateDataProvider")
        public void couponTest (String expected, String coupon){
            OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
            String actual = orderDetailsPage
                    .chooseRegionByValue(" Kyiv ")
                    .EnterNumberOfSafeWasteInput("20")
                    .EnterNumberOfTextileWaste20lInput("1")
                    .EnterNumberOfTextileWaste120lInput("1")
                    .EnterCertificateInput(coupon)
                    .clickOnActivateCertificateButton()
                    .getCertificateAlertMessage();
            Assert.assertTrue(actual.contains(expected));
        }
        @Description("Checks coupon activate button")
        @Issue("123")
        @Test(dataProvider = "certificateButtonProvider")
        public void couponActivateButtonTest (boolean expected, String coupon){
            OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
            boolean isActive = orderDetailsPage
                    .chooseRegionByValue(" Kyiv ")
                    .EnterNumberOfSafeWasteInput("20")
                    .EnterNumberOfTextileWaste20lInput("1")
                    .EnterNumberOfTextileWaste120lInput("1")
                    .EnterCertificateInput(coupon)
                    .getCertificateButtonStatus();
            Assert.assertEquals(isActive, expected);
        }

    @Description("Check does coupon cancel button work properly")
    @Issue("124")
    @Test
    public void couponCancelButtonTest (){
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput(codeValueActive)
                .clickOnCancelCertificateButton()
                .getCertificateInput();
        Assert.assertEquals(actual, "");
    }
        @AfterTest
        public void deleteCertificate () throws Exception {
            ecoNewsCertificateService.deleteCertificateByCode(codeValueActive);
        }
    }
