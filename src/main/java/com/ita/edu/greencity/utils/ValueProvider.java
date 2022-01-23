package com.ita.edu.greencity.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ValueProvider {
    private  Properties properties;

    public ValueProvider() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("src/main/resources/properties.properties");
        properties = new Properties();
        properties.load(fileInputStream);
    }


    public String getBaseURL() {
        return properties.getProperty("baseURL");
    }

    public String UbsHomePageURL() {
        return properties.getProperty("UbsHomePageURL");
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
}
