package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.LocalTestRunner;
import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderPageConfirmationTest extends TestRun {

    @Test
    public void paymentWIthExistingAddressTest() {
        String expected = "Your order is accepted";
        String actual = new UbsHomePage(driver).pressOrderCourier()
                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .chooseRegionByIndex(0).clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterNumberOfSafeWasteInput("1")
                .EnterNumberOfTextileWaste20lInput("1")
                .clickOnNextButton()
                .enterFirstName("Anna")
                .entersurname("Maria").enterEmail(provider.getEmail())
                .enterPhoneNumber("+380 (63) 114 46 78").clickOnNextButton()
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
        String actual = new UbsHomePage(driver).pressOrderCourier()
                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .chooseRegionByIndex(0).clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterNumberOfSafeWasteInput("1")
                .EnterNumberOfTextileWaste20lInput("1")
                .clickOnNextButton()
                .enterFirstName("Anna")
                .entersurname("Maria").enterEmail(provider.getEmail())
                .enterPhoneNumber("+380 (63) 114 46 78")
                .clickOnAddAddressButton()
                .clickOnCityField()
                .chooseCity(0)
                .chooseDistrict(4)
                .enterStreet("Sevastopol's'ka Square")
                .chooseStreet(0)
                .enterHouseNumber("19")
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

}
