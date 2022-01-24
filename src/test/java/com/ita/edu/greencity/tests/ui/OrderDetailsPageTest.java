package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderDetailsPageTest extends TestRun{
    @Test
    public void messageTest() {
        String expected = "Hello world";
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourier();

        SignInComponent signInComponent = new SignInComponent(driver);
        signInComponent.inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .chooseRegionByIndex(0).clickOnContinueButton();

        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.EnterNumberOfSafeWasteInput("1").EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCommentInput(expected)
                .clickOnNextButton()
                .clickOnBackButton();
        Assert.assertEquals(orderDetailsPage.getCommentInput(), expected);


    }
}
