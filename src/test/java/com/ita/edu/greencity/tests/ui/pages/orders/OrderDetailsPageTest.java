package com.ita.edu.greencity.tests.ui.pages.orders;
import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.utils.ValueProvider;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsCertificateService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class OrderDetailsPageTest extends TestRun {

EcoNewsCertificateService ecoNewsCertificateService = new EcoNewsCertificateService();
    private final String codeValue = "7777-6666";
    private final String statusValue = "ACTIVE";
    private final String expiration_dateValue = "2022-11-11 00:00:00";
    private final int pointsValue = 500;
      @BeforeTest
    public void AddCertificate() throws Exception {
          ecoNewsCertificateService.addCertificate(codeValue,statusValue,expiration_dateValue,pointsValue);
    }
@BeforeMethod
public void preConditions(){
    HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
             header.clickSignIn()
            .inputEmail(provider.getEmail())
            .inputPassword(provider.getPassword())
            .clickSignIn()
            .chooseRegionByIndex(0)
            .clickOnContinueButton();

}
    @Test
    public void messageTest() {
        String expected = "Hello world";
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage.chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCommentInput(expected)
                .clickOnNextButton()
                .clickOnBackButton()
                .getCommentInput();
         Assert.assertEquals(actual.trim(), expected);
    }

    @Test
    public void orderSumTest() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
                orderDetailsPage.chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1");
        float sumOfOfTextileWaste20l = Float.parseFloat(Arrays.stream(orderDetailsPage.getTextileWaste20lSum().split("\s")).toList().get(0));
        float sumOfOfTextileWaste120l = Float.parseFloat(Arrays.stream(orderDetailsPage.getTextileWaste120lSum().split("\s")).toList().get(0));
        float sumOfOfSumWaste = Float.parseFloat(Arrays.stream(orderDetailsPage.getSaveWasteSum().split("\s")).toList().get(0));
        float expectedSum =sumOfOfSumWaste+ sumOfOfTextileWaste120l+sumOfOfTextileWaste20l;
        float actualSum = Float.parseFloat(Arrays.stream(orderDetailsPage.getOrderAmount().split("\s")).toList().get(0));
        Assert.assertEquals(actualSum,expectedSum);
    }

    @Test
    public void couponTest() {
        String expected = "Certificate for 500 UAH activated";
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput(codeValue)
                .clickOnActivateCertificateButton()
                .getCertificateAlertMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @AfterTest
    public void checkRegisteredUser() throws Exception {
ecoNewsCertificateService.deleteCertificate(codeValue);
    }
}
