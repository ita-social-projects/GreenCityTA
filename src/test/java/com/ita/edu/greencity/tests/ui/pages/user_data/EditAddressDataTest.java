package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class EditAddressDataTest extends TestRun {
    @BeforeMethod
    public void loginToUBS(){
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }
    @DataProvider(name = "dataProvider")
    private Object[][] dataProvider() {
        return new Object[][]{
                {"Київ","Київська область","Святошинський", "вулиця Львівська" , "1" ,"1","1"},
                {"Київ","Київська область","Печерський", "Деміївська вулиця" , "1" ,"1","1"},
        };
    }
    @Test(dataProvider = "dataProvider")
    public void editAddress(String city, String region, String district,
                            String street, String number, String corpus, String entrance ) throws InterruptedException {
        new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnEditDataButton()
                .chooseAddress("Address №2")
                .setCityAddress(city)
                .setRegionAddress(region)
                .setDistrictAddress(district)
                .setStreetAddress(street)
                .setHouseNumberAddress(number)
                .setHouseCorpusAddress(corpus)
                .setEntranceNumberAddress(entrance)
                .clickOnSaveChangesButton();

        UserData userData = new UserData(driver);
        List<String> actualData = new ArrayList<>();
        actualData.add(userData.chooseAddressShow("Address №2").getCity());
        actualData.add(userData.chooseAddressShow("Address №2").getRegion());
        actualData.add(userData.chooseAddressShow("Address №2").getDistrict());
        actualData.add(userData.chooseAddressShow("Address №2").getStreet());
        actualData.add(userData.chooseAddressShow("Address №2").getHouse());
        actualData.add(userData.chooseAddressShow("Address №2").getCorpus());
        actualData.add(userData.chooseAddressShow("Address №2").getEntrance());
        SoftAssert asert = new SoftAssert();
        asert.assertEquals(actualData.get(0),city,"assert in city");
        asert.assertEquals(actualData.get(1),region,"assert in Region");
        asert.assertEquals(actualData.get(2),district,"assert in District");
        asert.assertEquals(actualData.get(3),street,"assert in Street");
        asert.assertEquals(actualData.get(4),number,"assert in House");
        asert.assertEquals(actualData.get(5),corpus,"assert in Corpus");
        asert.assertEquals(actualData.get(6),entrance,"assert in Entrance");
        asert.assertAll();

    }

}
