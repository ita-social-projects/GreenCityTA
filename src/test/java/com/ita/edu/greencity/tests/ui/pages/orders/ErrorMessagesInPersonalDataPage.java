package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorMessagesInPersonalDataPage extends TestRun {

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
    public void verifyNameErrorMessage(String name, String expectedErrorMessage) {
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actual = headerSignedOutComponent
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
        Assert.assertEquals(actual, expectedErrorMessage);
    }

    @Description("In this test we will verify error message with poor credential in 'Surname field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "surnameDataProvider")
    public void verifySurnameErrorMessage(String surname, String expectedErrorMessage) {
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actual = headerSignedOutComponent
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
        Assert.assertEquals(actual, expectedErrorMessage);
    }

    @Description("In this test we will verify error message with poor credential in 'Phone number field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "phoneNumberDataProvider")
    public void verifyPhoneNumberErrorMessage(String phoneNumber, String expectedErrorMessage) {
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actual = headerSignedOutComponent
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
        Assert.assertEquals(actual, expectedErrorMessage);
    }

    @Description("In this test we will verify error message with poor credential in 'Email field'")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "emailDataProvider")
    public void verifyEmailErrorMessage(String email, String expectedErrorMessage) {
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);

        String actual = headerSignedOutComponent
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
        Assert.assertEquals(actual, expectedErrorMessage);
    }
}