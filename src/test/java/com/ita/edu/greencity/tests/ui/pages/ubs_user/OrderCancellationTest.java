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

    @DataProvider
    public Object[][] popUpElements() {
        return new Object[][] {
                {"ua", "У разі скасування дане замовлення буде видалено. Чи дійсно Ви бажаєте скасувати замовлення?", "Ні", "Так"},
                {"en", "If you will cancel this order it will be deleted. Do you really want to cancel this order?", "No", "Yes"}
        };
    }

    @Description("test all pop-up elements localization")
    @Issue("105")
    @Test(dataProvider = "popUpElements")
    public void popUpElementsLocalization(String lang, String labelText, String noButton, String yesButton) {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        ubsUserOrders.getHeader()
                .clickLanguageSwitcher()
                .languageChoose(lang);


        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CancelPopUp cancelPopUp = ubsUserOrders.getOrderByNumber("86")
                .clickOnCancelButton();

        softAssert.assertEquals(cancelPopUp.getEnsuranceOfCancelingLabelText(), labelText, "Wrong label text");
        softAssert.assertEquals(cancelPopUp.getNoButton().getText(), noButton, "Wrong 'No' button text");
        softAssert.assertEquals(cancelPopUp.getYesButton().getText(), yesButton, "Wrong 'Yes' button text");
        softAssert.assertAll();

        cancelPopUp.clickOnNoButton();
    }

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
}
