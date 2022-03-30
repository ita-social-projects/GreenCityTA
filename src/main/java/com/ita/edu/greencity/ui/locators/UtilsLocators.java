package com.ita.edu.greencity.ui.locators;

import org.openqa.selenium.By;

public enum UtilsLocators {
   SPINNER(By.xpath("//span[contains(@class,'spinner')"));
    private By path;

    public By getPath(){
       return path;
    }

    UtilsLocators(By path) {
        this.path = path;
    }
}
