package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.ui.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidationOfCommentTest extends TestRun {
    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton().EnterNumberOfTextileWaste120lInput("2");
    }

    @Test
    public void CorrectCommentErrorMessage()
    {
        String expected = "You can't enter more than 255 characters.";
        String actual =  new OrderDetailsPage(driver).EnterCommentInput(TestHelpersUtils.generateRandomComment(256))
                .getCommentErrorMessage().getText();
        Assert.assertEquals(actual,expected);

    }

    @Test
    public void incorrectLenghtOfComment()
    {
        SoftAssert softAssert = new SoftAssert();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        boolean isDisplayed = orderDetailsPage.EnterCommentInput(TestHelpersUtils.generateRandomComment(256))
               .getCommentErrorMessage().isDisplayed();
        softAssert.assertTrue(isDisplayed,"Comment error message is not displayed");
        boolean isEnabled = orderDetailsPage.getNextButton().isEnabled();
        softAssert.assertFalse(isEnabled,"Next button is enable");
        softAssert.assertAll();
    }

    @Test
    public void correctLenghtOfComment()
    {
        SoftAssert softAssert = new SoftAssert();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        boolean isEnabled = orderDetailsPage.EnterCommentInput(TestHelpersUtils.generateRandomComment(138))
                .getNextButton()
                .isEnabled();
        softAssert.assertTrue(isEnabled,"Next button is not enable");
        isEnabled = orderDetailsPage.EnterCommentInput("")
                .getNextButton()
                .isEnabled();
        softAssert.assertTrue(isEnabled,"Next button is not enable");
        softAssert.assertAll();
    }


}
