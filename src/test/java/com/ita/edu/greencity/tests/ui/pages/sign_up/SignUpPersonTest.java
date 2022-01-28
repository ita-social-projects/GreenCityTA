package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUpPersonTest extends TestRun {

    static Connection con = null;
    private static Statement stmt;
    private String userEmail = RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
    private String userName = RandomStringUtils.randomAlphabetic(10);
    private String userPassword = "yachtOP_1";
    private String query1 = "SELECT id\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email = '" + userEmail + "' AND recipient_name ='" + userName + "'";
    private String query2 = "DELETE\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email ='" + userEmail + "' AND recipient_name ='" + userName + "'";

    @BeforeTest
    public void checkRegisteredUser() throws Exception {
        String dbUrl = provider.getDbUrl();
        String dbUsername = provider.getDbUsername();
        String dbPassword = provider.getDbPassword();
        Class.forName("org.postgresql.Driver").newInstance();
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
        signUpComponent.inputEmailIntoField(userEmail)
                .inputUserNameIntoField(userName)
                .inputPasswordIntoField(userPassword)
                .inputConfirmPasswordIntoField(userPassword)
                .clickOnSignUpButton();
        boolean disabled = signUpComponent.checkDisabledSignUpButton();
        Assert.assertFalse(disabled);
        String actual = new UbsHomePage(driver).getTitleH1Text();
        Assert.assertEquals(actual, "UBS Courier");
        signUpComponent.sleep(1000);
        SignInComponent signInComponent = new HeaderSignedOutComponent(driver).clickSignIn();
        signInComponent.inputEmail(userEmail)
                .inputPassword(userPassword)
                .clickSignIn();
        SelectRegion selectRegion = new SelectRegion(driver);
        selectRegion.clickOnCloseButton();
        String actualMessage = new UbsHomePage(driver).getTitleH1Text();
        Assert.assertEquals(actualMessage, "UBS Courier");
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
