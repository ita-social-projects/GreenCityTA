package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.orders.AddNewAddress;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderPageConfirmationTest extends TestRun {

    protected static ValueProvider provider;

    @Test
    public void paymentTest() {
        UbsHomePage ubsHomePage = new UbsHomePage();
        ubsHomePage.pressOrderCourier();
        SignInComponent signInComponent = new SignInComponent(driver);
        signInComponent.inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .chooseRegionByIndex(0).clickOnContinueButton();
        ubsHomePage.pressOrderCourier();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.EnterNumberOfSafeWasteInput("1");
        orderDetailsPage.EnterNumberOfTextileWaste20lInput("1");
        orderDetailsPage.EnterNumberOfTextileWaste120lInput("1");
        orderDetailsPage.clickOnNextButton();
        OrderPagePersonalData orderPagePersonalData = new OrderPagePersonalData(driver);
        orderPagePersonalData.enterFirstName("Anna");
        orderPagePersonalData.enterLastName("Maria");
        orderPagePersonalData.enterEmail(provider.getEmail());
        orderPagePersonalData.enterPhoneNumber("+380 (63) 114 46 78");
        orderPagePersonalData.clickOnAddAddressButton();
        AddNewAddress addNewAddress = new AddNewAddress(driver);

        orderPagePersonalData.clickOnNextButton();

        OrderPageConfirmation orderPageConfirmation = new OrderPageConfirmation(driver);
        String actual = orderPageConfirmation.choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail())
                .clickOnPayButton().clickOnContinueButton().getTextFromSuccessfulOrderMessage();
        String expected = "Ваше замовлення прийнято";

        Assert.assertTrue(actual.contains(expected), "Messages do not match");

    }
}
