package com.ita.edu.greencity.tests.ui.pages.sign_up;
import com.ita.edu.greencity.tests.ui.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class SignUpPersonTest extends TestRun{
    private static Statement stmt;
    static Connection con = null;
    private String userEmail = RandomStringUtils.randomAlphabetic(5) + "@gmail.com";
    private String userName = RandomStringUtils.randomAlphabetic(10);
    private String userPassword = RandomStringUtils.randomAlphabetic(9);
    private String query1 = "SELECT id\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email = '" +userEmail+ "' AND recipient_name ='" + userName+"'";
    private String query2 = "DELETE\n" +
            "FROM greencity_ubs.public.users\n" +
            "WHERE recipient_email ='" +userEmail+ "' AND recipient_name ='" + userName+"'";

    @BeforeTest
    public void checkRegisteredUser() throws Exception {
        try{
            String dbUrl = provider.getDbUrl();
            String dbUsername = provider.getDbUsername();
            String dbPassword = provider.getDbPassword();
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query1);
            String myiD = null;
            while (res.next())
            {
                myiD = res.getString(1);
                System.out.println("id" + myiD);
            }
            if(myiD != null){
                stmt.executeQuery(query2);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        SignUpComponent signUpComponent = new SignUpComponent(driver)
                .inputEmailIntoField(userEmail)
                .inputUserNameIntoField(userName)
                .inputPasswordIntoField(userPassword)
                .inputConfirmPasswordIntoField(userPassword)
                .clickOnSignUpButton();
        boolean disabled = signUpComponent.checkDisabledSignUpButton();
        Assert.assertFalse(disabled);
    }

    @AfterTest
    public void deleteRegisteredUser() throws Exception {
        ResultSet res = stmt.executeQuery(query1);
        String myiD = null;
        while (res.next())
        {
            myiD = res.getString(1);
            System.out.println("id" + myiD);
        }
        if(myiD != null){
            stmt.executeQuery(query2);
        }
        if (con != null) {
            con.close();
        }
    }
}
