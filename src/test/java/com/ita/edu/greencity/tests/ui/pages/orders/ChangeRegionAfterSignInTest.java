package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeRegionAfterSignInTest extends TestRun {
    public static String region = "Kyiv region";

    @Test
    public void changeRegionAfterSignIn() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        String actual = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue(region)
                .clickOnContinueButton().getLocationFromTitle().substring(11).trim();
        Assert.assertEquals(actual, region);
    }
}
