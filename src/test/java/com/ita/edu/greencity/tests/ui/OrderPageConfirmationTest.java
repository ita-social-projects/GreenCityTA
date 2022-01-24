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

import java.io.IOException;

public class OrderPageConfirmationTest extends TestRun {

    protected static ValueProvider provider;

    @Test
    public void paymentTest() throws IOException, InterruptedException {
        provider = new ValueProvider();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourier().inputEmail(provider.getEmail()).inputPassword(provider.getPassword())
                .clickSignIn().chooseRegionByIndex(0)
               .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterNumberOfSafeWasteInput("1")
                .EnterNumberOfTextileWaste20lInput("1")
                .clickOnNextButton();
        Thread.sleep(1000);

                OrderPagePersonalData orderPagePersonalData = new OrderPagePersonalData(driver);
                orderPagePersonalData.enterFirstName("Anna");


//
//               .enterLastName("Maria").enterEmail(provider.getEmail())
//               .enterPhoneNumber("+380 (63) 114 46 78").clickOnAddAddressButton()
//                .clickOnCityField().chooseCity(1)
//                .clickOnDistrictField().chooseDistrict(2)
//                .enterStreet("Почаївський провулок")
//               .enterHouseNumber("17").enterHouseCorpus("2").enterEntranceNumber("1")
//               .clickOnAddAddressButton().clickOnNextButton();


//     String actual =
//
//     OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
//                orderDetailsPage.EnterNumberOfTextileWaste120lInput("1").EnterNumberOfSafeWasteInput("1")
//                .EnterNumberOfTextileWaste20lInput("1")
//                .clickOnNextButton()
//                .enterFirstName("Anna")
//                .enterLastName("Maria").enterEmail(provider.getEmail())
//                .enterPhoneNumber("+380 (63) 114 46 78").clickOnAddAddressButton()
//                .clickOnCityField().chooseCity(1)
//                .clickOnDistrictField().chooseDistrict(2)
//                .enterStreet("Почаївський провулок")
//                .enterHouseNumber("17").enterHouseCorpus("2").enterEntranceNumber("1")
//                .clickOnAddAddressButton().clickOnNextButton();

//        SignInComponent signInComponent = new SignInComponent(driver);
//        signInComponent

//        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
//        orderDetailsPage

//        OrderPagePersonalData orderPagePersonalData = new OrderPagePersonalData(driver);
//        orderPagePersonalData

//        OrderPageConfirmation orderPageConfirmation = new OrderPageConfirmation(driver);
//        String actual = orderPageConfirmation.choosePaymentMethod().clickOnOrderButton()
//                .cardNumberInput(provider.getCardNumber())
//                .expiryDateInput(provider.getExpiryDate())
//                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail())
//                .clickOnPayButton().clickOnContinueButton().getTextFromSuccessfulOrderMessage();
//        String expected = "Ваше замовлення прийнято";
//
//        Assert.assertTrue(actual.contains(expected), "Messages do not match");

    }
}
