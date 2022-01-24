package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderDetailsPageTest extends TestRun{
    @Test
    public void messageTest() {
        String expected = "Hello world";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByIndex(0)
                .clickOnContinueButton();

        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
           String  actual =  orderDetailsPage.EnterNumberOfSafeWasteInput("1")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCommentInput(expected)
                .clickOnNextButton()
                .clickOnBackButton()
                .getCommentInput();
         Assert.assertEquals(actual, expected);


    }
}
