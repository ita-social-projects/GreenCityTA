package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.Assert;
import org.testng.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderPageConfirmationTest extends TestRun {

    final String textileWaste120lAmount = "1";
    final String safeWasteAmount = "1";
    final String textileWaste20lAmount = "2";
    final String userFirstName = "John";
    final String userLastName = "Doe";
    final String userPhoneNumber = "+380 (63) 114 46 78";
    final int indexOfCity = 0;
    final int indexOfDistrict = 4;
    final int indexOfStreet = 0;
    final String streetToAdd = "Sevastopol's'ka Square";
    final String houseNumberToAdd = "19";

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourier()
                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .chooseRegionByIndex(0).clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(textileWaste120lAmount)
                .EnterNumberOfSafeWasteInput(safeWasteAmount)
                .EnterNumberOfTextileWaste20lInput(textileWaste20lAmount)
                .clickOnNextButton()
                .enterFirstName(userFirstName)
                .entersurname(userLastName).enterEmail(provider.getEmail())
                .enterPhoneNumber(userPhoneNumber);
    }

    @Test
    public void paymentWIthExistingAddressTest() {
        String expectedMessage = "Your order is accepted";
        String actualMessage = new OrderPagePersonalData(driver)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2())
                .emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Messages do not match");
    }

    @Test
    public void paymentWIthAddingNewAddressTest() {
        String expectedMessage = "Your order is accepted";
        String actualMessage = new OrderPagePersonalData(driver)
                .clickOnAddAddressButton()
                .clickOnCityField()
                .chooseCity(indexOfCity)
                .chooseDistrict(indexOfDistrict)
                .enterStreet(streetToAdd)
                .chooseStreet(indexOfStreet)
                .enterHouseNumber(houseNumberToAdd)
                .clickOnAddAddressButton()
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Messages do not match");
    }

    @Test
    public void theTotalSumOfOrderIdentityTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();

        String totalAmountOfTextileWaste120l = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(1, 5)
                .split("\s")).toList().get(0);
        String totalAmountOfSafeWaste = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(2, 5)
                .split("\s")).toList().get(0);
        String totalAmountOfTextileWaste20l = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(3, 5)
                .split("\s")).toList().get(0);

        double sumOfAllWasteTypesTotals = orderPageConfirmation
                .transformToDoubleValue(totalAmountOfTextileWaste120l)
                + orderPageConfirmation.transformToDoubleValue(totalAmountOfSafeWaste)
                + orderPageConfirmation.transformToDoubleValue(totalAmountOfTextileWaste20l);

        double expectedOrderAmount = orderPageConfirmation
                .transformToDoubleValue(Arrays.stream(orderPageConfirmation
                .getTotalSumWithCurrency(0).split("\s")).toList().get(0));

        double expectedAmountDue = orderPageConfirmation
                .transformToDoubleValue(Arrays.stream(orderPageConfirmation
                .getTotalSumWithCurrency(1).split("\s")).toList().get(0));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sumOfAllWasteTypesTotals, expectedOrderAmount, "Order amount is not the same");
        softAssert.assertEquals(sumOfAllWasteTypesTotals, expectedAmountDue, "Amount due is not the same");
        softAssert.assertAll();
    }

    @Test
    public void verifyOrderSavingTest() {
        String actualMessage = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert();
        String numberOfOrder = actualMessage.substring(28, 32);
        String expectedMessage = "Now you can find your order " + numberOfOrder + " in your personal account and continue processing it at any time";
        Assert.assertEquals(actualMessage, expectedMessage, "Messages do not match");
    }
}
