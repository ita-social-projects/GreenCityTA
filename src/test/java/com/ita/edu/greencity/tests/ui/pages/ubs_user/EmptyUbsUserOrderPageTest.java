package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.UbsUserTestRun;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class EmptyUbsUserOrderPageTest extends UbsUserTestRun {

    @DataProvider
    private Object[][] emptyOrderPageLabelAndButton() {
        return new Object[][] {
                {"ua", "Ви ще не зробили замовлень у нас.", "Нове замовлення"},
                {"en", "You haven't made any orders with us yet.", "New order"}
        };
    }

    @Description("test localization of label and new order button")
    @Issue("107")
    @Test(dataProvider = "emptyOrderPageLabelAndButton")
    public void verifyLabelAndButtonLocalization(String language, String label, String newOrderButtonText) {

        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();
        ubsUserOrders.getHeader()
                .clickLanguageSwitcher()
                .languageChoose(language);

        softAssert.assertEquals(ubsUserOrders.getEmptyOrdersPageLabel(), label,
                "Order page isn't empty");
        softAssert.assertEquals(ubsUserOrders.getNewOrderButton().getText(), newOrderButtonText,
                "New order button has wrong text");
        softAssert.assertAll();
    }

    @Description("test presence of new order button")
    @Issue("107")
    @Test
    public void verifyNewOrderButtonOnUbsUserOrderPage() {

        Assert.assertTrue(new UbsUserOrders(driver).getNewOrderButton().isDisplayed(),
                "There is no New Order Button on the page");

    }

}
