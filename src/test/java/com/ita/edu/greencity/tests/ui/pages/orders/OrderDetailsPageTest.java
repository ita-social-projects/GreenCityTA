package com.ita.edu.greencity.tests.ui.pages.orders;
import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.utils.ValueProvider;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OrderDetailsPageTest extends TestRun {

    static Connection con = null;
    //private static Statement stmt;
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
//    stmt.executeUpdate(query2);
//    stmt.executeUpdate(query1);
    }

    @Test
    public void messageTest() {
        String expected = "Hello world";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        String actual = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .chooseRegionByIndex(0)
                .clickOnContinueButton()
                .chooseRegionByValue(" Kyiv ")
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput("6666-7777")
                .clickOnActivateCertificateButton()
                .EnterCommentInput(expected)
                .clickOnNextButton()
                .clickOnBackButton()
                .getCommentInput();
         Assert.assertEquals(actual.trim(), expected);
    }
    @AfterTest
    public void checkRegisteredUser() throws Exception {
    Statement stmt = con.createStatement();
    stmt.executeUpdate(query2);
        con.close();
    }
}
