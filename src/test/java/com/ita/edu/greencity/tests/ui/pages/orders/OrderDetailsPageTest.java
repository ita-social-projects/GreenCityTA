package com.ita.edu.greencity.tests.ui.pages.orders;
import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class OrderDetailsPageTest extends TestRun {

    static Connection con = null;
    private String query1 = "INSERT INTO greencity_ubs.public.certificate (code,status, expiration_date, points) VALUES ('6666-7777', 'ACTIVE', '2022-11-11 00:00:00', 500)";
    private String query2 = "DELETE  from greencity_ubs.public.certificate where code = '6666-7777'";
      @BeforeTest
    public void AddCertificate() throws Exception {
        String dbUrl = provider.getDbUrl();
        String dbUsername = provider.getDbUsername();
        String dbPassword = provider.getDbPassword();
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement stmt = con.createStatement();
    stmt.executeUpdate(query1);
    }
@BeforeMethod
public void preConditions(){
    HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
             header.clickSignIn()
            .inputEmail(provider.getEmail())
            .inputPassword(provider.getPassword())
            .clickSignIn()
            .chooseRegionByIndex(0)
            .clickOnContinueButton();

}
    @Test
    public void messageTest() {
        String expected = "Hello world";
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage.chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCommentInput(expected)
                .clickOnNextButton()
                .clickOnBackButton()
                .getCommentInput();
         Assert.assertEquals(actual.trim(), expected);
    }

    @Test
    public void orderSumTest() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
                orderDetailsPage.chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1");
        float sumOfOfTextileWaste20l = Float.parseFloat(Arrays.stream(orderDetailsPage.getTextileWaste20lSum().split("\s")).toList().get(0));
        float sumOfOfTextileWaste120l = Float.parseFloat(Arrays.stream(orderDetailsPage.getTextileWaste120lSum().split("\s")).toList().get(0));
        float sumOfOfSumWaste = Float.parseFloat(Arrays.stream(orderDetailsPage.getSaveWasteSum().split("\s")).toList().get(0));
        float expectedSum =sumOfOfSumWaste+ sumOfOfTextileWaste120l+sumOfOfTextileWaste20l;
        float actualSum = Float.parseFloat(Arrays.stream(orderDetailsPage.getOrderAmount().split("\s")).toList().get(0));
        Assert.assertEquals(actualSum,expectedSum);
    }

    @Test
    public void couponTest() {
        String expected = "Certificate for 500 UAH activated";
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput("6666-7777")
                .clickOnActivateCertificateButton()
                .getCertificateAlertMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @AfterTest
    public void checkRegisteredUser() throws Exception {
    Statement stmt = con.createStatement();
    stmt.executeUpdate(query2);
        con.close();
    }
}
