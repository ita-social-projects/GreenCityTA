package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.orders.AddNewAddress;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckAddNewAddressIsDisabled extends TestRun {

    @Test
    public void checkAddNewAddressIsDisabled() throws InterruptedException, IOException {
        ValueProvider provider = new ValueProvider();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        AddNewAddress addNewAddress = new AddNewAddress(driver);
        ubsHomePage.pressOrderCourier()
        .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
        .clickOnContinueButton()
        .EnterNumberOfTextileWaste120lInput("5")
        .clickOnNextButton().clickOnAddAddressButton();
        Assert.assertTrue(addNewAddress.getAddAddressButton().isEnabled());
    }
}
