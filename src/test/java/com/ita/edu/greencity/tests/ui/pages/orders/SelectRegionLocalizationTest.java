package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.ui.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SelectRegionLocalizationTest extends TestRun {
    @DataProvider(name = "languageChanger-provider")
    public Object[][] dataProviderMethod() {
        return new String[][]{{"ua","uk"}, {"en","en"}};
    }

    @Test(dataProvider = "languageChanger-provider")
    void ChangeRegionLocalization(String lang, String expected) {
        SoftAssert softAssert = new SoftAssert();

        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);

        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        SelectRegion selectRegion = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();
        softAssert.assertEquals(TestHelpersUtils.getLanguage(selectRegion.getBottomText()), expected,
                "Incorrect language for BOTTOM text");
        softAssert.assertEquals(TestHelpersUtils.getLanguage(selectRegion.getTitleText()),expected,
                "Incorrect language for TITLE text");
        softAssert.assertAll();
    }
}
