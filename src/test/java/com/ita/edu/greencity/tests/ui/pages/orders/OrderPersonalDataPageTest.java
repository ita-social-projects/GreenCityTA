package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderPersonalDataPageTest extends TestRun {

    final String NUMBER_OF_TEXTILE_WASTE_120 = "5";
    final String EXPECTED_MESSAGE_OF_ABSENCE_ANY_ADDRESS_EN = "You have no addresses added. Please add an address.";
    final String EXPECTED_MESSAGE_OF_ABSENCE_ANY_ADDRESS_UA = "У Вас немає доданих адрес. Додайте, будь ласка, адресу.";

    @DataProvider
    private Object[][] nameDataProvider() {
        final String expectedWithInsufficientCharacters = "The field contains insufficient characters.";
        final String expectedWithForbiddenCharacters = "The field contains forbidden characters.";
        final String expectedWhenYouEnterMoreThanAllowed = "Maximum number of characters exceeded.";

        return new Object[][]{
                {"q", expectedWithInsufficientCharacters},
                {"12$", expectedWithForbiddenCharacters},
                {"qwertyuiopasdfghjklzxcvbn", expectedWhenYouEnterMoreThanAllowed}
        };
    }

    @DataProvider
    private Object[][] surnameDataProvider() {
        final String expectedWithInsufficientCharacters = "The field contains insufficient characters.";
        final String expectedWithForbiddenCharacters = "The field contains forbidden characters.";
        final String expectedWhenYouEnterMoreThanAllowed = "Maximum number of characters exceeded.";

        return new Object[][]{
                {"q", expectedWithInsufficientCharacters},
                {"12$", expectedWithForbiddenCharacters},
                {"qwertyuiopasdfghjklzxcvbn", expectedWhenYouEnterMoreThanAllowed}
        };
    }

    @DataProvider
    private Object[][] phoneNumberDataProvider() {
        final String expectedWithEmptyFields = "This field is required.";
        final String expectedIncorrectNumber = "Enter the correct phone number";

        return new Object[][]{
                {"", expectedWithEmptyFields},
                {"+380 (12) 345 67 89", expectedIncorrectNumber}
        };
    }

    @DataProvider
    private Object[][] emailDataProvider() {
        final String expectedWithEmptyFields = "This field is required.";
        final String expectedIncorrectEmail = "Enter the correct email.";

        return new Object[][]{
                {"", expectedWithEmptyFields},
                {"testgreencity323gmail.com", expectedIncorrectEmail}
        };
    }

    @Description("In this test we will verify error message with poor credential in 'Name field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "nameDataProvider")
    public void verifyNameErrorMessage(String name, String expectedErrorMessage){
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actualErrorMessage = headerSignedOutComponent
                .clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("5")
                .clickOnNextButton()
                .enterFirstName(name)
                .clickForGetMessage()
                .getTextFromNameErrorMessage();
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);
    }

    @Description("In this test we will verify error message with poor credential in 'Surname field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "surnameDataProvider")
    public void verifySurnameErrorMessage(String surname, String expectedErrorMessage){
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actualErrorMessage = headerSignedOutComponent
                .clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("5")
                .clickOnNextButton()
                .entersurname(surname)
                .clickForGetMessage()
                .getTextFromSurnameErrorMessage();
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);
    }

    @Description("In this test we will verify error message with poor credential in 'Phone number field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "phoneNumberDataProvider")
    public void verifyPhoneNumberErrorMessage(String phoneNumber, String expectedErrorMessage){
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actualErrorMessage = headerSignedOutComponent
                .clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("5")
                .clickOnNextButton()
                .enterPhoneNumber(phoneNumber)
                .clickForGetMessage()
                .getTextFromPhoneNumberErrorMessage();
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);
    }

    @Description("In this test we will verify error message with poor credential in 'Email field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "emailDataProvider")
    public void verifyEmailErrorMessage(String email, String expectedErrorMessage){
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actualErrorMessage = headerSignedOutComponent
                .clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput("5")
                .clickOnNextButton()
                .enterEmail(email)
                .clickForGetMessage()
                .getTextFromEmailErrorMessage();
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage);
    }


    @Description("Verifying the correctness of localization for initial message of absence any addresses in 'Garbage collection address' block of 'Personal Data' page.")
    @Severity(SeverityLevel.NORMAL)
    @Issue("GC-2070")
    @Test
    public void verificationForMessageOfAbsenceAnyAddressesOnPersonalDataPage(){
        String actualMessageEn = new UbsHomePage(driver)
                .pressOrderCourier()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .getTextFromAbsenceAnyAddresses();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessageEn,EXPECTED_MESSAGE_OF_ABSENCE_ANY_ADDRESS_EN);

        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose("ua");
        String actualMessageUa = new OrderDetailsPage(driver)
                .clickOnNextButton()
                .getTextFromAbsenceAnyAddresses();

        softAssert.assertEquals(actualMessageUa,EXPECTED_MESSAGE_OF_ABSENCE_ANY_ADDRESS_UA);
        softAssert.assertAll();

    }
}
