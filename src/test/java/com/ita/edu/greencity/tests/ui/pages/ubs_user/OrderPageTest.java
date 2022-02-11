package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.UbsUserTestRun;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderPageTest extends UbsUserTestRun {

    @Description("test presence of 'current orders' and 'order history' tabs on the page")
    @Issue("106")
    @Test
    public void pageTabsPresenceTest() {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(ubsUserOrders.getCurrentOrdersTabButton().isDisplayed(),
                "There is no current orders tab");
        softAssert.assertTrue(ubsUserOrders.getOrderHistoryTabButton().isDisplayed(),
                "There is no order history tab");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] pageTabs() {
        return new Object[][] {
                {"ua", "Поточні замовлення", "Історія замовлень"},
                {"en", "Current orders", "Order history"}
        };
    }

    @Description("test order page elements localization")
    @Issue("106")
    @Test(dataProvider = "pageTabs")
    public void pageTabsLocalizationTest(String language, String firstTabText, String secondTabText) {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        ubsUserOrders.getHeader()
                .clickLanguageSwitcher()
                .languageChoose(language);

        softAssert.assertEquals(ubsUserOrders.getCurrentOrdersTabButton().getText(), firstTabText,
                "Wrong current orders tab label text");
        softAssert.assertEquals(ubsUserOrders.getOrderHistoryTabButton().getText(), secondTabText,
                "Wrong order history tab label text");
        softAssert.assertAll();
    }

    @Description("test 'current orders' tab is selected by default")
    @Issue("106")
    @Test
    public void currentOrdersTabSelectedByDefaultTest() {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);

        Assert.assertTrue(Boolean.parseBoolean(ubsUserOrders.getCurrentOrdersTab().getAttribute("aria-selected")),
                "Current orders tab is not selected by defaul");
    }
}
