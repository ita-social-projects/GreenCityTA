package com.ita.edu.greencity.tests.ui;

import com.ita.edu.greencity.ui.pages.orders.AddNewAddress;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.ValueProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckAddNewAddressButton extends TestRun {

    private final String NUMBER_OF_TEXTILE_WASTE_120 = "5";
    private final String STREET = "Sevastopol's'ka Square";
    private final int INDEX_CITY = 0;
    private final int INDEX_DISTRICT = 4;
    private final int INDEX_STREET = 0;

    @Test
    public void checkAddNewAddressIsNotClickable() throws IOException {
        ValueProvider provider = new ValueProvider();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        AddNewAddress addNewAddress = new AddNewAddress(driver);
        ubsHomePage.pressOrderCourier()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .clickOnAddAddressButton();
        Assert.assertFalse(addNewAddress.getAddAddressButton().isEnabled());
    }

    @Test
    public void checkAddNewAddressIsClickable() throws IOException, InterruptedException {
        ValueProvider provider = new ValueProvider();
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        AddNewAddress addNewAddress = new AddNewAddress(driver);
        ubsHomePage.pressOrderCourier()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .clickOnCityField()
                .chooseCity(INDEX_CITY)
                .chooseDistrict(INDEX_DISTRICT)
                .enterStreet(STREET)
                .chooseStreet(INDEX_STREET)
                .enterHouseNumber("19")
                .enterStreet(STREET)
                .chooseStreet(INDEX_STREET);
        Assert.assertTrue(addNewAddress.getAddAddressButton().isEnabled());
    }
}
