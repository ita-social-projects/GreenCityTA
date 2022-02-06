package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import org.testng.Assert;
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

    @Description("Verify whether the result of addition all types of wastes is the same as one calculated in UBS")
    @Test
    public void theTotalSumOfOrderIdentityTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();

        String totalAmountOfTextileWaste120l = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(4, 5)
                .split("\s")).toList().get(0);
        String totalAmountOfSafeWaste = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(5, 5)
                .split("\s")).toList().get(0);
        String totalAmountOfTextileWaste20l = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(6, 5)
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

    @Description("Verify that order saving functionality")
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


}
