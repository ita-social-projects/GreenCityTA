package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.google_account.GoogleSignInInputEmailPopUp;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUpWithGoogleTest extends TestRun {

    static Connection con = null;
    private static Statement stmt;
    private final String userEmail = "registertesttest88@gmail.com";
    private final String query1 = "SELECT id\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email = '" + userEmail + "'";
    private final String query2 = "DELETE\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email ='" + userEmail  + "'";

    @BeforeTest
    public void checkRegisteredUser() throws Exception {
        String dbUrl = provider.getDbUrl();
        String dbUsername = provider.getDbUsername();
        String dbPassword = provider.getDbPassword();
        Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query1);
        String myiD = null;
        while (res.next()) {
            myiD = res.getString(1);
            System.out.println("id" + myiD);
        }
        if (myiD != null) {
            stmt.executeQuery(query2);
        }
    }

    @Test
    public void test() {
        SignUpComponent signUpComponent = new HeaderSignedOutComponent(driver).clickSignUp();
        String userPassword = "Tetsregistr_1";
        signUpComponent.clickOnSignUpWithGoogleButton();
        new GoogleSignInInputEmailPopUp(driver).inputEmailIntoField(userEmail)
                .clickOnContinueButton()
                .inputPasswordIntoField(userPassword)
                .clickOnContinueButton();
        new SelectRegion(driver).clickOnCloseButton();
        signUpComponent.clickOnExitButton();
        String actualUserName = new HeaderSignedInComponent(driver).getUserName();
        Assert.assertEquals(actualUserName,"Tetsregistr Tetsregistr");
    }

    @AfterTest
    public void deleteRegisteredUser() throws Exception {
        ResultSet res = stmt.executeQuery(query1);
        String myiD = null;
        while (res.next()) {
            myiD = res.getString(1);
            System.out.println("id" + myiD);
        }
        if (myiD != null) {
            stmt.executeQuery(query2);
        }
        if (con != null) {
            con.close();
        }
    }
}
