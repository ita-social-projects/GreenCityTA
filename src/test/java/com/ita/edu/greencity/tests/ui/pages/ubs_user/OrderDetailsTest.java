package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.UbsUserTestRun;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderDetailsTest extends UbsUserTestRun {

    @Test
    public void test123() {

        String expectedVisibleElement = "visibility: visible;";
        String expectedHiddenElement = "visibility: hidden;";

        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            Thread.sleep(35000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String firstOrderMustdBeOpened = ubsUserOrders.
                getOrderByNumber("45")
                .clickOnOrderDetailsArrowDown()
                .getOrderDetailsElement()
                .getAttribute("style");

        softAssert.assertEquals(firstOrderMustdBeOpened.equals(expectedVisibleElement),
                "First order details are not opened");

        String secondOrderMustBeOpened = ubsUserOrders
                .getOrderByNumber("46")
                .clickOnOrderDetailsArrowDown()
                .getOrderDetailsElement()
                .getAttribute("style");

        softAssert.assertEquals(secondOrderMustBeOpened.equals(expectedVisibleElement),
                "Second order details are not opened");

        String firstOrderMustBeClosed = ubsUserOrders.getOrderByNumber("45")
                .getOrderDetailsElement()
                .getAttribute("style");

        softAssert.assertTrue(firstOrderMustBeClosed.contains(expectedHiddenElement),
                "First order details was not closed after opening second order details");

        softAssert.assertAll();
    }
}
