package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.orders.AddNewAddress;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddNewAddressTest extends TestRun {

    final String NUMBER_OF_TEXTILE_WASTE_120 = "5";
    final String STREET = "Sevastopol's'ka Square";
    final String NUMBER_OF_HOUSE = "19";
    final int INDEX_CITY = 0;
    final int INDEX_DISTRICT = 4;
    final int INDEX_STREET = 0;


    @Description("In this test we will verify that button isn't clickable, when user don't fill mandatory fields, and after filling all mandatory fields the button is clickable")
    @Issue("32")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkAddNewAddressButton() {
        AddNewAddress addNewAddress = new UbsHomePage(driver).pressOrderCourier()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .clickOnAddAddressButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(addNewAddress.getAddAddressButton().isEnabled(), "Address button is disabled");

        addNewAddress.addAddress(INDEX_CITY, INDEX_DISTRICT, STREET, INDEX_STREET, NUMBER_OF_HOUSE);

        softAssert.assertTrue(addNewAddress.getAddAddressButton().isEnabled(), "Address button is enabled");
        softAssert.assertAll();
    }
}