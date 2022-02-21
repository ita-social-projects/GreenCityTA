package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PaymentByFondyTest extends TestRun {

    final String TEXTILE_WASTE_120L_AMOUNT = "1";
    final String SAFE_WASTE_AMOUNT = "1";
    final String TEXTILE_WASTE_20l_AMOUNT = "2";
    final String USER_FIRST_NAME = "John";
    final String USER_LAST_NAME = "Doe";
    final String USER_PHONE_NUMBER = "+380 63 123 44 99";
    final int INDEX_OF_CITY = 0;
    final int INDEX_OF_DISTRICT = 4;
    final int INDEX_OF_STREET = 0;
    final String STREET_TO_ADD = "Sevastopol's'ka Square";
    final String HOUSE_NUMBER_TO_ADD = "19";
    final String CORPUS_TO_ADD = "2";
    final String ENTRANCE_TO_ADD = "3";


    @BeforeMethod(description = "Navigate to Order confirmation page")
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourierUnlogin()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(TEXTILE_WASTE_120L_AMOUNT)
                .EnterNumberOfSafeWasteInput(SAFE_WASTE_AMOUNT)
                .EnterNumberOfTextileWaste20lInput(TEXTILE_WASTE_20l_AMOUNT)
                .clickOnNextButton()
                .enterFirstName(USER_FIRST_NAME)
                .entersurname(USER_LAST_NAME).enterEmail(provider.getEmail())
                .enterPhoneNumber(USER_PHONE_NUMBER);
    }

//    public void beforeMethod(ITestContext iTestContext) {
//        super.beforeMethod(iTestContext);
//        UbsHomePage ubsHomePage = new UbsHomePage(driver);
//        ubsHomePage.pressOrderCourier()
//                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
//                .chooseRegionByIndex(0).clickOnContinueButton()
//                .EnterNumberOfTextileWaste120lInput(TEXTILE_WASTE_120L_AMOUNT)
//                .EnterNumberOfSafeWasteInput(SAFE_WASTE_AMOUNT)
//                .EnterNumberOfTextileWaste20lInput(TEXTILE_WASTE_20l_AMOUNT)
//                .clickOnNextButton()
//                .enterFirstName(USER_FIRST_NAME)
//                .entersurname(USER_LAST_NAME).enterEmail(provider.getEmail())
//                .enterPhoneNumber(USER_PHONE_NUMBER);
//    }

    @Description("Verify whether the error message about wrong email address is displayed")
    @Test
    public void errorMessageEmailTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();
        String expected = "Please enter a valid email";
        String actual = orderPageConfirmation
                .clickOnOrderButton()
                .chooseLanguage("English").emailInput("testgreencity@gmail.")
                .unfocus().focusToEmailField().getEmailErrorMessage();
        Assert.assertEquals(actual, expected);
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
                .addFullAddress(INDEX_OF_CITY, INDEX_OF_DISTRICT, STREET_TO_ADD, INDEX_OF_STREET, HOUSE_NUMBER_TO_ADD, CORPUS_TO_ADD, ENTRANCE_TO_ADD)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Messages do not match");
    }

    @Description("Verify order payment functionality by checking whether order with an appropriate number and " +
            "payment status appears in user cabinet")
    @Test
    public void verifyPaymentApprovalThroughOrderNumberAndPaymentStatusTest() {
        String numberOfOrder = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert().substring(28, 33);
        String actualOrderNumber = new HeaderSignedInComponent(driver).clickUserMenu().clickUbsUser()
                .getOrderByNumber(numberOfOrder).getOrderId();
        String expectedPaymentStatus = "Paid";
        String actualPaymentStatus = new HeaderSignedInComponent(driver).clickUserMenu().clickUbsUser()
                .getOrderByNumber(numberOfOrder).getPaymentStatus();
        Assert.assertEquals(actualOrderNumber,numberOfOrder, "Order with such number does not exist");
        Assert.assertEquals(actualPaymentStatus, expectedPaymentStatus, "Payment statuses do not match");
    }

    @Test
    public void declinedPaymentFunctionalityTest() {
        String expectedMessage = "Unfortunately, your payment was unsuccessful";
        String actualMessage = new OrderPagePersonalData(driver)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getInvalidCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2())
                .emailInput(provider.getEmail()).clickOnPayButton()
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

    @Description("Verify make other order functionality")
    @Test
    public void makeOtherOrderTest() {
        String actual = new OrderPagePersonalData(driver)
                .clickOnNextButton()
                .clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2())
                .emailInput(provider.getEmail())
                .clickOnPayButton().clickOnTheLink().clickOnContinueButton()
                .clickOnMakeOtherOrderButton().getTitleText();
        String expected = "Welcome to UBS!";
        Assert.assertTrue(actual.contains(expected));
    }

}
