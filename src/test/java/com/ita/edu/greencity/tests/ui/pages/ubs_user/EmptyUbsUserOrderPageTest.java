package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmptyUbsUserOrderPageTest extends TestRun {

    @Test
    public void verifyThatUserWithNoOrdersHasAnEmptyOrderPage() {

        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail("grabarskiy02@gmail.com")
                .inputPassword("123456Qw/")
                .clickSignIn()
                .chooseRegionByIndex(0)
                .clickOnContinueButton();

        String expected = "You haven't made any orders with us yet.";

        HeaderSignedInComponent headerSignedInComponent = new HeaderSignedInComponent(driver);

        String actual = headerSignedInComponent.clickUserMenu()
                .clickUbsUser()
                .getEmptyOrdersPageLabel();

        Assert.assertEquals(actual, expected, "Not empty page!");
    }

    @Test
    public void verifyNewOrderButtonOnUbsUserOrderPage() {

        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail("grabarskiy02@gmail.com")
                .inputPassword("123456Qw/")
                .clickSignIn()
                .chooseRegionByIndex(0)
                .clickOnContinueButton();

        HeaderSignedInComponent headerSignedInComponent = new HeaderSignedInComponent(driver);

        boolean newOrderButtonIsDisplayed = headerSignedInComponent
                .clickUserMenu()
                .clickUbsUser()
                .getNewOrderButton()
                .isDisplayed();

        Assert.assertTrue(newOrderButtonIsDisplayed);
    }

}
