package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.LocalTestRunner;
import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.ChangeLanguageAlert;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        String expected = "Your order is accepted";
        String actual = new OrderPagePersonalData(driver)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .acceptAlert()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage();
        Assert.assertTrue(actual.contains(expected), "Messages do not match");
    }

    @Test
    public void paymentWIthAddingNewAddressTest() {
        String expected = "Your order is accepted";
        String actual = new OrderPagePersonalData(driver)
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
                .acceptAlert()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage();
        Assert.assertTrue(actual.contains(expected), "Messages do not match");
    }

    @Test
    public void theTotalSumOfOrderIdentityTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();

        String total1 = orderPageConfirmation.chooseOneElementFromYourOrderTable(1, 5).substring(0, 6);
        String total2 = orderPageConfirmation.chooseOneElementFromYourOrderTable(2, 5).substring(0, 6);
        String total3 = orderPageConfirmation.chooseOneElementFromYourOrderTable(3, 5).substring(0, 6);

        double sumOfAllWasteTypesTotals = orderPageConfirmation.transformToDoubleValue(total1)
                + orderPageConfirmation.transformToDoubleValue(total2)
                + orderPageConfirmation.transformToDoubleValue(total3);

        double expectedOrderAmount = orderPageConfirmation.transformToDoubleValue(orderPageConfirmation
                .getTotalSumWithCurrency(0).substring(0, 6));

        double expectedAmountDue = orderPageConfirmation.transformToDoubleValue(orderPageConfirmation
                .getTotalSumWithCurrency(1).substring(0, 6));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sumOfAllWasteTypesTotals, expectedOrderAmount, "Order amount is not the same");
        softAssert.assertEquals(sumOfAllWasteTypesTotals, expectedAmountDue, "Amount due is not the same");
        softAssert.assertAll();
    }

    @Test
    public void verifyOrderSavingTest() {
        String actual = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert();
        String numberOfOrder = actual.substring(28, 32);
        String expected = "Now you can find your order " + numberOfOrder + " in your personal account and continue processing it at any time";
        Assert.assertEquals(actual, expected, "Messages do not match");
    }

    @Test
    public void localizationRelevanceOfCurrencyTest() {
        String actualResultBeforeLanguageChange = new OrderPagePersonalData(driver).clickOnNextButton()
                .getTotalSumWithCurrency(0).substring(7);
        String expectedResultBeforeLanguageChange = "UAH";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResultBeforeLanguageChange, expectedResultBeforeLanguageChange);
        HeaderComponent header = new HeaderComponent(driver);
        header.clickLanguageSwitcher().languageChoose("UA");
        ChangeLanguageAlert changeLanguageAlert = new ChangeLanguageAlert(driver);
        changeLanguageAlert.dismissAlert();
        String actualResultAfterLanguageChange = new OrderPageConfirmation(driver).getTotalSumWithCurrency(0).substring(7);
        String expectedResultAfterLanguageChange = "грн";
        softAssert.assertEquals(actualResultAfterLanguageChange, expectedResultAfterLanguageChange);
        softAssert.assertAll();
    }
}
