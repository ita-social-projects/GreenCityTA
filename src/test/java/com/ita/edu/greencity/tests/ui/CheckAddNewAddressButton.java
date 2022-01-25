package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.orders.AddNewAddress;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckAddNewAddressButton extends TestRun {

    @Test
    public void checkAddNewAddressIsNotClickable() throws InterruptedException, IOException {
        ValueProvider provider = new ValueProvider();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        AddNewAddress addNewAddress = new AddNewAddress(driver);
        ubsHomePage.pressOrderCourier()
        .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
        .clickOnContinueButton()
        .EnterNumberOfTextileWaste120lInput("5")
        .clickOnNextButton()
                .clickOnAddAddressButton();
        Assert.assertFalse(addNewAddress.isClickable());
    }

    @Test
    public void checkAddNewAddressIsClickable() throws InterruptedException, IOException {
        ValueProvider provider = new ValueProvider();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        AddNewAddress addNewAddress = new AddNewAddress(driver);
        ubsHomePage.pressOrderCourier()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("5")
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .chooseCity(2)
                .chooseDistrict(1)
                .enterStreet("Ярославська вулиця")
                .enterHouseNumber("1").enterHouseCorpus("2").enterEntranceNumber("3");
        Assert.assertTrue(addNewAddress.isClickable());
    }
}
