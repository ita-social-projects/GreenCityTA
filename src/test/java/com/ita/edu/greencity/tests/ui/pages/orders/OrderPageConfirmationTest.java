package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderPageConfirmationTest extends TestRun {

    final String TEXTILE_WASTE_120L_AMOUNT = "1";
    final String SAFE_WASTE_AMOUNT = "1";
    final String TEXTILE_WASTE_20l_AMOUNT = "2";
    final String USER_FIRST_NAME = "John";
    final String USER_LAST_NAME = "Doe";
    final String USER_PHONE_NUMBER = "+380 63 123 44 99";
    final String NEW_USER_FIRST_NAME = "Emily";
    final String NEW_USER_LAST_NAME = "Winston";
    final String NEW_USER_EMAIL = "new_user@gmail.com";
    final String NEW_USER_PHONE_NUMBER = "+380 98 777 55 66";

    @BeforeMethod(description = "Navigate to Order confirmation page")
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourier()
                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .chooseRegionByIndex(0).clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(TEXTILE_WASTE_120L_AMOUNT)
                .EnterNumberOfSafeWasteInput(SAFE_WASTE_AMOUNT)
                .EnterNumberOfTextileWaste20lInput(TEXTILE_WASTE_20l_AMOUNT)
                .clickOnNextButton()
                .enterFirstName(USER_FIRST_NAME)
                .entersurname(USER_LAST_NAME).enterEmail(provider.getEmail())
                .enterPhoneNumber(USER_PHONE_NUMBER);
    }

    @Description("Verify whether the result of addition all types of wastes is the same as one calculated in UBS")
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

    @Description("Verify order saving functionality by checking whether the appropriate message appears")
    @Test
    public void verifyOrderSavingInPopupMessageTest() {
        String actualMessage = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert();
        String numberOfOrder = actualMessage.substring(28, 32);
        String expectedMessage = "Now you can find your order " + numberOfOrder + " in your personal account and continue processing it at any time";
        Assert.assertEquals(actualMessage, expectedMessage, "Messages do not match");
    }

    @Description("Verify order saving functionality by checking whether appropriate number appears in user cabinet")
    @Test
    public void verifyOrderSavingThroughOrderNumberTest() {
        String expectedNumberOfOrder = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton().getTextFromSuccessfulSavingAlert().substring(28, 32);
        String actualNumberOfOrder = new HeaderSignedInComponent(driver).clickUbsUser()
                .getOrderByNumber(expectedNumberOfOrder).getOrderId();
        Assert.assertEquals(actualNumberOfOrder, expectedNumberOfOrder, "Order with expected number does not exist");
    }

    @Description("Verify order saving functionality by checking whether appropriate order status appears in user cabinet")
    @Test
    public void verifyOrderSavingThroughOrderStatusTest() {
        String numberOfOrder = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert().substring(28, 32);
        String expectedOrderStatus = "Formed";
        String actualOrderStatus = new HeaderSignedInComponent(driver).clickUbsUser()
                .getOrderByNumber(numberOfOrder).getOrderStatus();
        Assert.assertEquals(actualOrderStatus, expectedOrderStatus, "Order statuses do not match");
    }

    @Description("Verify order deleting functionality")
    @Test
    public void verifyOrderDeletingTest() {
        String actualMessage = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnDeleteButton()
                .getHomePageTitle();
        String expectedMessage = "It's even easier than before!";
        Assert.assertEquals(actualMessage, expectedMessage, "Messages do not match");
    }

    @Description("Verify whether after language changing the currency of order is changed")
    @Test
    public void localizationRelevanceOfCurrencyTest() {
        String actualResultBeforeLanguageChange = Arrays.stream(new OrderPagePersonalData(driver).clickOnNextButton()
                .getTotalSumWithCurrency(0).split("\s")).toList().get(1);
        String expectedResultBeforeLanguageChange = "UAH";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResultBeforeLanguageChange, expectedResultBeforeLanguageChange);
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose("UA");
        String actualResultAfterLanguageChange = Arrays.stream(new OrderDetailsPage(driver)
                .clickOnNextButton().clickOnNextButton()
                .getTotalSumWithCurrency(0).split("\s")).toList().get(1);
        String expectedResultAfterLanguageChange = "грн";
        softAssert.assertEquals(actualResultAfterLanguageChange, expectedResultAfterLanguageChange);
        softAssert.assertAll();
    }


    @Description("Verify that info about recipient is updated after changing")
    @Test
    public void verifyRecipientCredentialsTest(){
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderPageConfirmation.getRecipientName(),USER_FIRST_NAME);
        softAssert.assertEquals(orderPageConfirmation.getRecipientSurname(),USER_LAST_NAME);
        softAssert.assertEquals(orderPageConfirmation.getRecipientEmailAddress(),provider.getEmail());
        softAssert.assertEquals(orderPageConfirmation.getRecipientPhoneNumber(),USER_PHONE_NUMBER);

        orderPageConfirmation.clickOnBackButton().enterFirstName(NEW_USER_FIRST_NAME)
                        .entersurname(NEW_USER_LAST_NAME)
                .enterEmail(NEW_USER_EMAIL)
                .enterPhoneNumber(NEW_USER_PHONE_NUMBER).clickOnNextButton();

        softAssert.assertEquals(orderPageConfirmation.getRecipientName(),NEW_USER_FIRST_NAME);
        softAssert.assertEquals(orderPageConfirmation.getRecipientSurname(),NEW_USER_LAST_NAME);
        softAssert.assertEquals(orderPageConfirmation.getRecipientEmailAddress(),NEW_USER_EMAIL);
        softAssert.assertEquals(orderPageConfirmation.getRecipientPhoneNumber(),NEW_USER_PHONE_NUMBER);
        softAssert.assertAll();
    }


}
