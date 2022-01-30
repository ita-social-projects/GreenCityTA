package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUpPersonTest extends TestRun {

    static Connection con = null;
    private static Statement stmt;
    private final String userEmail = RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
    private final String userName = RandomStringUtils.randomAlphabetic(10);
    private final String query1 = "SELECT id\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email = '" + userEmail + "' AND recipient_name ='" + userName + "'";
    private final String query2 = "DELETE\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email ='" + userEmail + "' AND recipient_name ='" + userName + "'";

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
        String userPassword = "yachtOP_1";
        signUpComponent.inputEmailIntoField(userEmail)
                .inputUserNameIntoField(userName)
                .inputPasswordIntoField(userPassword)
                .inputConfirmPasswordIntoField(userPassword)
                .clickOnSignUpButton();
        boolean isDisabled = signUpComponent.checkDisabledSignUpButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isDisabled, "SignUp button is disabled!");
        String expectedAlert = signUpComponent.getTextOfSuccessRegistrationAlert();
        softAssert.assertEquals(expectedAlert, "Congratulations! You have successfully registered on the site. Please confirm your email address in the email box.", "No alert!");
        softAssert.assertAll();
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
