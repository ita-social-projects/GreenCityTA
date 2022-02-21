package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.ui.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderPageConfirmationTest extends TestRun {

    final String TEXTILE_WASTE_120L_AMOUNT = "1";
    final String SAFE_WASTE_AMOUNT = "1";
    final String TEXTILE_WASTE_20l_AMOUNT = "2";
    final String USER_FIRST_NAME = "John";
    final String USER_LAST_NAME = "Doe";
    final String USER_PHONE_NUMBER = "+380 63 123 44 99";
    final String NEW_USER_FIRST_NAME = "Emily";
    final String NEW_USER_LAST_NAME = "Winston";
    final String NEW_USER_EMAIL = "new_user@gmail.com";
    final String NEW_USER_PHONE_NUMBER = "+380 98 777 55 66";
    final int INDEX_OF_CITY = 0;
    final int INDEX_OF_DISTRICT = 4;
    final int INDEX_OF_STREET = 0;
    final String STREET_TO_ADD = "Sevastopol's'ka Square";
    final String CORPUS_TO_ADD = "2";
    final String ENTRANCE_TO_ADD = "3";
    final int NEW_INDEX_OF_CITY = 0;
    final int NEW_INDEX_OF_DISTRICT = 4;
    final int NEW_INDEX_OF_STREET = 0;
    final String NEW_STREET_TO_ADD = "Lesi Ukrainky Boulevard";
    final String NEW_HOUSE_NUMBER_TO_ADD = "11";
    final String NEW_CORPUS_TO_ADD = "3";
    final String NEW_ENTRANCE_TO_ADD = "2";

    @BeforeMethod(description = "Navigate to Order confirmation page")
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourier()
                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(TEXTILE_WASTE_120L_AMOUNT)
                .EnterNumberOfSafeWasteInput(SAFE_WASTE_AMOUNT)
                .EnterNumberOfTextileWaste20lInput(TEXTILE_WASTE_20l_AMOUNT)
                .clickOnNextButton()
                .enterFirstName(USER_FIRST_NAME)
                .entersurname(USER_LAST_NAME).enterEmail(provider.getEmail())
                .enterPhoneNumber(USER_PHONE_NUMBER);
    }

//    public void beforeMethod(ITestContext iTestContext) {
//        super.beforeMethod(iTestContext);
//        UbsHomePage ubsHomePage = new UbsHomePage(driver);
//        ubsHomePage.pressOrderCourier()
//                .inputEmail(provider.getEmail()).inputPassword(provider.getPassword()).clickSignIn()
//                .chooseRegionByIndex(0).clickOnContinueButton()
//                .EnterNumberOfTextileWaste120lInput(TEXTILE_WASTE_120L_AMOUNT)
//                .EnterNumberOfSafeWasteInput(SAFE_WASTE_AMOUNT)
//                .EnterNumberOfTextileWaste20lInput(TEXTILE_WASTE_20l_AMOUNT)
//                .clickOnNextButton()
//                .enterFirstName(USER_FIRST_NAME)
//                .entersurname(USER_LAST_NAME).enterEmail(provider.getEmail())
//                .enterPhoneNumber(USER_PHONE_NUMBER);
//    }


    @Description("Verify whether the result of addition all types of wastes is the same as one calculated in UBS")
    @Test
    public void theTotalSumOfOrderIdentityTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();
        String totalAmountOfTextileWaste120l = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(1, 5)
                .split("\s")).toList().get(0);
        String totalAmountOfSafeWaste = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(2, 5)
                .split("\s")).toList().get(0);
        String totalAmountOfTextileWaste20l = Arrays.stream(orderPageConfirmation
                .chooseOneElementFromYourOrderTable(3, 5)
                .split("\s")).toList().get(0);

        double sumOfAllWasteTypesTotals = orderPageConfirmation
                .transformToDoubleValue(totalAmountOfTextileWaste120l)
                + orderPageConfirmation.transformToDoubleValue(totalAmountOfSafeWaste)
                + orderPageConfirmation.transformToDoubleValue(totalAmountOfTextileWaste20l);
        double expectedOrderAmount = orderPageConfirmation
                .transformToDoubleValue(Arrays.stream(orderPageConfirmation
                        .getTotalSumWithCurrency(0).split("\s")).toList().get(0));
        double expectedAmountDue = orderPageConfirmation
                .transformToDoubleValue(Arrays.stream(orderPageConfirmation
                        .getTotalSumWithCurrency(1).split("\s")).toList().get(0));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sumOfAllWasteTypesTotals, expectedOrderAmount, "Order amount is not the same");
        softAssert.assertEquals(sumOfAllWasteTypesTotals, expectedAmountDue, "Amount due is not the same");
        softAssert.assertAll();
    }

    @Description("Verify order saving functionality by checking whether the appropriate message appears")
    @Test
    public void verifyOrderSavingInPopupMessageTest() {
        String actualMessage = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert();
        String numberOfOrder = actualMessage.substring(28, 33);
        String expectedMessage = "Now you can find your order " + numberOfOrder + "in your personal account and continue processing it at any time";
        Assert.assertEquals(actualMessage, expectedMessage, "Messages do not match");
    }


 /*   @Description("Verify order deleting functionality")
=======
    @Description("Verify order saving functionality by checking whether appropriate number appears in user cabinet")
    @Test
    public void verifyOrderSavingThroughOrderNumberTest() {
        String expectedNumberOfOrder = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton().getTextFromSuccessfulSavingAlert().substring(28, 33);
        String actualNumberOfOrder = new HeaderSignedInComponent(driver).clickUserMenu().clickUbsUser()
                .getOrderByNumber(expectedNumberOfOrder).getOrderId();
        Assert.assertEquals(actualNumberOfOrder, expectedNumberOfOrder, "Order with expected number does not exist");
    }

    @Description("Verify order saving functionality by checking whether appropriate order status appears in user cabinet")
    @Test
    public void verifyOrderSavingThroughOrderStatusTest() {
        String numberOfOrder = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnSaveButton()
                .getTextFromSuccessfulSavingAlert().substring(28, 33);
        String expectedOrderStatus = "Formed";
        String actualOrderStatus = new HeaderSignedInComponent(driver).clickUserMenu().clickUbsUser()
                .getOrderByNumber(numberOfOrder).getOrderStatus();
        Assert.assertEquals(actualOrderStatus, expectedOrderStatus, "Order statuses do not match");
    }

    @Description("Verify order deleting functionality")
>>>>>>> b40bf5fa8837a2b30753eec9d5622d1ed10d085b
    @Test
    public void verifyOrderDeletingTest() {
        String actualMessage = new OrderPagePersonalData(driver).clickOnNextButton()
                .clickOnCancelButton()
                .clickOnDeleteButton()
                .getHomePageTitle();
        String expectedMessage = "It's even easier than before!";
        Assert.assertEquals(actualMessage, expectedMessage, "Messages do not match");
    }
*/
    @Description("Verify whether after language changing the currency of order is changed")
    @Test
    public void localizationRelevanceOfCurrencyTest() {
        String actualResultBeforeLanguageChange = Arrays.stream(new OrderPagePersonalData(driver).clickOnNextButton()
                .getTotalSumWithCurrency(0).split("\s")).toList().get(1);
        String expectedResultBeforeLanguageChange = "UAH";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResultBeforeLanguageChange, expectedResultBeforeLanguageChange,"Currencies are different before language change");
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose("UA");
        String actualResultAfterLanguageChange = Arrays.stream(new OrderDetailsPage(driver)
                .clickOnNextButton().clickOnNextButton()
                .getTotalSumWithCurrency(0).split("\s")).toList().get(1);
        String expectedResultAfterLanguageChange = "грн";
        softAssert.assertEquals(actualResultAfterLanguageChange, expectedResultAfterLanguageChange,"Currencies are different after language change");
        softAssert.assertAll();
    }

    @Description("Verify that info about the address of export is updated after language changing")
    @Link("https://github.com/ita-social-projects/GreenCity/issues/3868")
    @Test
    public void verifyExportAddressLocalizationTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver)
                .clickOnAddAddressButton()
                .addFullAddress(INDEX_OF_CITY, INDEX_OF_DISTRICT, STREET_TO_ADD, INDEX_OF_STREET, "19", CORPUS_TO_ADD, ENTRANCE_TO_ADD)
                .refreshPage()
                .clickOnNextButton()
                .refreshPage()
                .clickOnNextButton()
                .clickOnChooseAddressButton(1)
                .clickOnNextButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderPageConfirmation.getCity(), "city Kyiv","Cities don't match");
        softAssert.assertEquals(orderPageConfirmation.getDistrict(), "district Solom'yans'kyi","Districts don't match");
        softAssert.assertEquals(orderPageConfirmation.getStreet(), "Sevastopol's'ka Square","Streets don't match");
        softAssert.assertEquals(orderPageConfirmation.getHouseNumber(), "19","House numbers don't match");
        softAssert.assertEquals(orderPageConfirmation.getCorpus(), "corp 2","Corpus numbers don't match");
        softAssert.assertEquals(orderPageConfirmation.getEntrance(), "entrance 3","Entrances don't match");
        softAssert.assertEquals(orderPageConfirmation.getRegion(), "Kyiv region","Regions don't match");
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose("UA");
        new OrderDetailsPage(driver).clickOnNextButton().clickOnNextButton();
        softAssert.assertEquals(orderPageConfirmation.getCity(), "м. Київ","City is translated incorrectly");
        softAssert.assertEquals(orderPageConfirmation.getDistrict(), "район Солом'янський","District is translated incorrectly");
        softAssert.assertEquals(orderPageConfirmation.getStreet(), "Севастопольська площа","Street is translated incorrectly");
        softAssert.assertEquals(orderPageConfirmation.getCorpus(), "корпус 2","Corpus is translated incorrectly");
        softAssert.assertEquals(orderPageConfirmation.getEntrance(), "під'їзд 3","Entrance is translated incorrectly");
        softAssert.assertEquals(orderPageConfirmation.getRegion(), "Київська область","Region is translated incorrectly");
        softAssert.assertAll();
    }


    @Description("Verify that info about the address of export is updated after its changing")
    @Link("https://github.com/ita-social-projects/GreenCity/issues/3868")
    @Test
    public void verifyExportAddressRelevanceAfterAddressChangeTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver)
                .clickOnAddAddressButton()
                .addFullAddress(INDEX_OF_CITY, INDEX_OF_DISTRICT, STREET_TO_ADD, INDEX_OF_STREET, "46", CORPUS_TO_ADD, ENTRANCE_TO_ADD)
                .refreshPage()
                .clickOnNextButton()
                .clickOnChooseAddressButton(1)
                .clickOnNextButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderPageConfirmation.getCity(), "city Kyiv","Cities don't match");
        softAssert.assertEquals(orderPageConfirmation.getDistrict(), "district Solom'yans'kiy","Districts don't match");
        softAssert.assertEquals(orderPageConfirmation.getStreet(), STREET_TO_ADD,"Streets don't match");
        softAssert.assertEquals(orderPageConfirmation.getHouseNumber(), "46","House numbers don't match");
        softAssert.assertEquals(orderPageConfirmation.getCorpus(), "corp 2","Corpus numbers don't match");
        softAssert.assertEquals(orderPageConfirmation.getEntrance(), "entrance 3","Entrances don't match");
        softAssert.assertEquals(orderPageConfirmation.getRegion(), "Kyiv region","Regions don't match");

        orderPageConfirmation.clickOnBackButton().clickOnAddAddressButton()
                .addFullAddress(NEW_INDEX_OF_CITY, NEW_INDEX_OF_DISTRICT, NEW_STREET_TO_ADD, NEW_INDEX_OF_STREET, NEW_HOUSE_NUMBER_TO_ADD, NEW_CORPUS_TO_ADD, NEW_ENTRANCE_TO_ADD)
                .refreshPage()
                .clickOnNextButton()
                .clickOnChooseAddressButton(2)
                .clickOnNextButton();
        softAssert.assertEquals(orderPageConfirmation.getCity(), "city Kyiv","City is not changed");
        softAssert.assertEquals(orderPageConfirmation.getDistrict(), "district Pechers'kiy","District is not changed");
        softAssert.assertEquals(orderPageConfirmation.getStreet(), NEW_STREET_TO_ADD,"Street is not changed");
        softAssert.assertEquals(orderPageConfirmation.getHouseNumber(), NEW_HOUSE_NUMBER_TO_ADD,"House number is not changed");
        softAssert.assertEquals(orderPageConfirmation.getCorpus(), "corp 3","Corpus is not changed");
        softAssert.assertEquals(orderPageConfirmation.getEntrance(), "entrance 2","Entrance is not changed");
        softAssert.assertEquals(orderPageConfirmation.getRegion(), "Kyiv region","Region is not changed");
        softAssert.assertAll();
    }

    @Description("Verify that info about recipient is updated after changing")
    @Test
    public void verifyRecipientCredentialsTest() {
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver).clickOnNextButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderPageConfirmation.getRecipientName(), USER_FIRST_NAME,"User names don't match");
        softAssert.assertEquals(orderPageConfirmation.getRecipientSurname(), USER_LAST_NAME,"User surnames don't match");
        softAssert.assertEquals(orderPageConfirmation.getRecipientEmailAddress(), provider.getEmail(),"User emails don't match");
        softAssert.assertEquals(orderPageConfirmation.getRecipientPhoneNumber(), USER_PHONE_NUMBER,"User phone numbers don't match");

        orderPageConfirmation.clickOnBackButton().enterFirstName(NEW_USER_FIRST_NAME)
                .entersurname(NEW_USER_LAST_NAME)
                .enterEmail(NEW_USER_EMAIL)
                .enterPhoneNumber(NEW_USER_PHONE_NUMBER).clickOnNextButton();

        softAssert.assertEquals(orderPageConfirmation.getRecipientName(), NEW_USER_FIRST_NAME,"User name is not changed");
        softAssert.assertEquals(orderPageConfirmation.getRecipientSurname(), NEW_USER_LAST_NAME,"User surname is not changed");
        softAssert.assertEquals(orderPageConfirmation.getRecipientEmailAddress(), NEW_USER_EMAIL,"User email is not changed");
        softAssert.assertEquals(orderPageConfirmation.getRecipientPhoneNumber(), NEW_USER_PHONE_NUMBER,"User phone number is not changed");
        softAssert.assertAll();
    }

    @Description("Verify that after adding the eco store order it appears at the confirmation page")
    @Test
    public void verifyEcoStoreFunctionality() {
        OrderDetailsPage orderDetailsPage = new OrderPagePersonalData(driver).clickOnBackButton();

        String firstOrderNumber = TestHelpersUtils.generateRandomOrderNumber();
        String secondOrderNumber = TestHelpersUtils.generateRandomOrderNumber();
        String actualFirstOrderNumber = orderDetailsPage
                .clickOnYesWaitingStoreOrderCheckmark()
                .EnterOrderNumberInputs(firstOrderNumber, 0)
                .clickOnAddAnotherNumberButton()
                .EnterOrderNumberInputs(secondOrderNumber, 1)
                .clickOnNextButton().clickOnNextButton()
                .getEcoStoreNumber(0);
        String actualSecondOrderNumber = new OrderPageConfirmation(driver).getEcoStoreNumber(1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualFirstOrderNumber, firstOrderNumber,"Numbers of first order don't match");
        softAssert.assertEquals(actualSecondOrderNumber, secondOrderNumber,"Numbers of second order don't match");
        softAssert.assertAll();
    }


}
