package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderDetailsPageTest extends TestRun {


    @BeforeMethod
    public void preConditions() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
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
        float expectedSum = sumOfOfSumWaste + sumOfOfTextileWaste120l + sumOfOfTextileWaste20l;
        float actualSum = Float.parseFloat(Arrays.stream(orderDetailsPage.getOrderAmount().split("\s")).toList().get(0));
        Assert.assertEquals(actualSum, expectedSum);
    }

    @Description("Checks if orders from eco store saves when we go to 'Personal data' page and return to 'Order details' page")
    @Issue("117")
    @Test
    public void ecoStoreOrderTest() {
        String orderNumber1 = TestHelpersUtils.generateRandomOrderNumber();
        String orderNumber2 = TestHelpersUtils.generateRandomOrderNumber();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual1 = orderDetailsPage.chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .clickOnYesWaitingStoreOrderCheckmark()
                .EnterOrderNumberInputs(orderNumber1, 0)
                .clickOnAddAnotherNumberButton()
                .EnterOrderNumberInputs(orderNumber2, 1)
                .clickOnNextButton()
                .clickOnBackButton()
                .getOrderNumberInputs(0);
        String actual2 = orderDetailsPage.getOrderNumberInputs(1);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actual1, orderNumber1);
        softAssert.assertEquals(actual2, orderNumber2);
        softAssert.assertAll();

        softAssert.assertEquals(actual1, orderNumber1);
        softAssert.assertEquals(actual2, orderNumber2);

    }
}
