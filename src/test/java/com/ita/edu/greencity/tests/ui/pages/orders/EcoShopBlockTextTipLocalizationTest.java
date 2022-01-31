package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static java.util.Map.entry;

public class EcoShopBlockTextTipLocalizationTest extends TestRun {
    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }

    @DataProvider(name = "languageChanger-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{
                {"ua", Map.ofEntries(
                        entry("tipTextHint","Якщо Ви не змогли знайти номер замовлення, вкажіть в коментарі" +
                                " прізвище та ім’я, на які робили замовлення.")
                )},
                {"en", Map.ofEntries(
                        entry("tipTextHint","If you could not find the order number, please indicate your " +
                                "name and surname for which you placed the order, in the comments.")
                ) }

        };
    }

    @Test(dataProvider = "languageChanger-provider")
    void TipTextLocalization(String lang, Map<String,String> map) {
        SoftAssert softAssert = new SoftAssert();
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);
        String actual = new OrderDetailsPage(driver).getTipTextHint();
        softAssert.assertEquals(actual, map.get("tipTextHint"),"Incorrect localization");
    }

}
