package com.ita.edu.greencity.tests.ui.pages.orders;
import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.ui.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsCertificateService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderDetailsPageTest extends TestRun {


EcoNewsCertificateService ecoNewsCertificateService = new EcoNewsCertificateService();
    private final String codeValueActive = "7777-6666";
    private final String statusValueActive = "ACTIVE";
    private final String expiration_dateValue = "2022-11-11 00:00:00";
    private final int pointsValue = 500;


    @DataProvider
    private Object[][] certificateDataProvider() {
        final String codeValueActive = "7777-6666";
        final String codeValueUsed = ecoNewsCertificateService.selectRandomUsedCertificate();

        return new Object[][]{
                {"Certificate for 500 UAH activated", codeValueActive},
                {"Certificate has already been used ", codeValueUsed},
        };
    }
      @BeforeTest
    public void AddCertificate() throws Exception {
          ecoNewsCertificateService.deleteCertificateByCode(codeValueActive);
          ecoNewsCertificateService.addCertificate(codeValueActive,statusValueActive,expiration_dateValue,pointsValue);
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
    @Description("Checks if comment saves when we go to 'Personal data' page and return to 'Order details' page")
    @Issue("88")
    @Test
    public void messageTest() {
        String expected = TestHelpersUtils.generateRandomComment(20);
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
    @Description("Checks if 'Order amount' is counted properly")
    @Issue("89")
    @Test
    public void orderAmountTest() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
                orderDetailsPage.chooseRegionByValue(" Kyiv region")
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
    @Description("Checks coupon alert")
    @Issue("90")
    @Test(dataProvider = "certificateDataProvider")
    public void couponTest(String expected,String coupon) {
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

    @Description("Checks if orders from eco store saves when we go to 'Personal data' page and return to 'Order details' page")
    @Issue("117")
    @Test
    public void ecoStoreWaitingTest() {
        String orderNumber1 = TestHelpersUtils.generateRandomOrderNumber();
        String orderNumber2 = TestHelpersUtils.generateRandomOrderNumber();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual1 = orderDetailsPage.chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .clickOnYesWaitingStoreOrderCheckmark()
                .EnterOrderNumberInputs(orderNumber1,0)
                .clickOnAddAnotherNumberButton()
                .EnterOrderNumberInputs(orderNumber2,1)
                .clickOnNextButton()
                .clickOnBackButton()
                .getOrderNumberInputs(0);
        String actual2 = orderDetailsPage.getOrderNumberInputs(1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual1,orderNumber1);
        softAssert.assertEquals(actual2,orderNumber2);

    }
    @AfterTest
    public void deleteCertificate() throws Exception {
        ecoNewsCertificateService.deleteCertificateByCode(codeValueActive);
    }
}
