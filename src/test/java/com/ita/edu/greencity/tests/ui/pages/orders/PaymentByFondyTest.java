package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PaymentByFondyTest extends TestRun {

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


    @BeforeMethod(description = "Navigate to Order confirmation page")
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

    @Description("Verify whether the error message about wrong email address is displayed")
    @Test
    public void errorMessageEmailTest(){
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();
        String expected = "Please enter a valid email";
        String actual =  orderPageConfirmation
                .clickOnOrderButton()
                .chooseLanguage("English").emailInput("testgreencity@gmail.").unfocus().focusToEmailField().getEmailErrorMessage();
        Assert.assertEquals(actual,expected);
    }

    @Description("Verify that order is made using existing address")
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

    @Description("Verify that order is made using new added address")
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
                .enterStreet(streetToAdd)
                .chooseStreet(indexOfStreet)
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

    @Description("Verify that the total sum of order in UBS is the same as in the payment system")
    @Test
    public void orderAmountInUbsAndFondyIdentityTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();
        double orderAmountInUbs = orderPageConfirmation
                .transformToDoubleValue(Arrays.stream(orderPageConfirmation
                        .getTotalSumWithCurrency(0).split("\s")).toList().get(0));

        double orderAmountInFondy = orderPageConfirmation
                .transformToDoubleValue(orderPageConfirmation
                        .clickOnOrderButton()
                        .getTotalPay()
                        .replace(",", "."));
        Assert.assertEquals(orderAmountInUbs, orderAmountInFondy);
    }

}
