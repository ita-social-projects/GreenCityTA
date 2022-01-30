package com.ita.edu.greencity.utils;

import java.io.*;
import java.util.Properties;

public class ValueProvider {
    private Properties properties;

    public ValueProvider() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/properties.properties");
        properties = new Properties();
        properties.load(fileInputStream);
    }


    public String getBaseURL() {
        return properties.getProperty("baseURL");
    }

    public String getUbsHomePageURL() {
        return properties.getProperty("UbsHomePageURL");
    }

    public String getLocalUbsHomePageURL() {
        return properties.getProperty("localUbsHomePageURL");
    }

    public String getEmail() {
        return properties.getProperty("email");
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getCardNumber() {
        return properties.getProperty("cardNumber");
    }

    public String getExpiryDate() {
        return properties.getProperty("expiryDate");
    }

    public String getCVV2() {
        return properties.getProperty("CVV2");
    }

    public String getDbUrl() {
        return properties.getProperty("dbUrl");
    }

    public String getDbUsername() {
        return properties.getProperty("dbUsername");
    }

    public String getDbPassword() {
        return properties.getProperty("dbPassword");
    }

    public void setPassword(String newPassword) throws IOException {
        FileReader reader =  new FileReader("src/main/resources/properties.properties");
        properties.load(reader);
        properties.getProperty("password");
        properties.setProperty("password",newPassword);
        OutputStream os = new FileOutputStream("src/main/resources/properties.properties");
        properties.store(os,"new password for rollback" );
    }
}
