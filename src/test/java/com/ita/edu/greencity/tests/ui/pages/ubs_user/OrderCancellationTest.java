package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.UbsUserTestRun;
import com.ita.edu.greencity.ui.pages.ubs_user.UbsUser;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.CancelPopUp;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.OrdersContainer;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsOrdersEntity;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsOrdersService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderCancellationTest extends UbsUserTestRun {

    EcoNewsOrdersService ecoNewsOrdersService = new EcoNewsOrdersService();

    @Description("test capability to cancel only orders with order status 'formed' and payment status 'unpaid'")
    @Issue("105")
    @Test()
    public void verifyThatOnlyFormedAndUnpaidOrdersCanBeCancelled() {

        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        OrdersContainer neededElement = ubsUserOrders
                .getOrderByOrderAndPaymentStatuses("Formed", "Unpaid");

        String orderId = neededElement.getOrderId();

        softAssert.assertTrue(neededElement.getCancelButton().isDisplayed());

        softAssert.assertEquals(neededElement.clickOnCancelButton()
                .clickOnYesButton()
                .getOrderByNumber(orderId), null, "Order was not cancelled");

        softAssert.assertEquals(ecoNewsOrdersService.getById(Long.parseLong(orderId)), null,
                "Order was not deleted from DataBase");

        softAssert.assertAll();
    }

    @Description("test 'No' button in cancellation pop-up window")
    @Issue("105")
    @Test
    public void verifyThatFormedAndUnpaidOrdersAreNotCancelledByClickingNoInPopUpWindow() {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        OrdersContainer neededElement = ubsUserOrders
                .getOrderByOrderAndPaymentStatuses("Formed", "Unpaid");

        String orderId = neededElement.getOrderId();

        softAssert.assertNotEquals(neededElement.clickOnCancelButton()
                .clickOnNoButton()
                .getOrderByNumber(orderId), null, "Order was cancelled");

        softAssert.assertNotEquals(ecoNewsOrdersService.getById(Long.parseLong(orderId)), null,
                "Order was deleted from DataBase");

        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] popUpElements() {
        return new Object[][] {
                {"ua", "У разі скасування дане замовлення буде видалено. Чи дійсно Ви бажаєте скасувати замовлення?", "Ні", "Так"},
                {"en", "If canceled, this order will be deleted. Do you really want to cancel the order?", "No", "Yes"}
        };
    }

    @Description("test pop-up elements localization")
    @Issue("105")
    @Test(dataProvider = "popUpElements")
    public void popUpElementsLocalization(String lang, String labelText, String noButton, String yesButton) {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        ubsUserOrders.getHeader()
                .clickLanguageSwitcher()
                .languageChoose(lang);

        CancelPopUp cancelPopUp = ubsUserOrders
                .getOrderByOrderAndPaymentStatuses("Formed", "Unpaid")
                .clickOnCancelButton();

        softAssert.assertEquals(cancelPopUp.getEnsuranceOfCancelingLabelText(), labelText, "Wrong label text");
        softAssert.assertEquals(cancelPopUp.getNoButton().getText(), noButton, "Wrong 'No' button text");
        softAssert.assertEquals(cancelPopUp.getYesButton().getText(), yesButton, "Wrong 'Yes' button text");
        softAssert.assertAll();

        cancelPopUp.clickOnNoButton();
    }


}
