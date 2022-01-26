package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class UserData extends BasePage {

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.edit")
    private WebElement editData;

    @FindBy(how = How.XPATH, using = "//form/div[2]/div[2]/p")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//div[3]/form/div[2]/div[2]/p")
    private WebElement phone;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.open")
    private WebElement changePassword;

    @FindBy(how = How.CSS, using = "button.btn.btn-outline-success.delete")
    private WebElement deleteProfile;

    @FindBy(how = How.CSS, using = "div.address.ng-untouched.ng-pristine.ng-valid.ng-star-inserted")
    private List<WebElement> allAdresses;

    public UserData(WebDriver driver) {
        super(driver);
    }

    public EditUserData clickOnEditDataButton()  {
        editData.click();
        return  new EditUserData(driver);

    }
    public String getTextFromEmailField(){
        return email.getText();
    }
    public String getTextFromPhoneField(){
        return phone.getText();
    }
    public ChangePassword clickOnChangePasswordButton(){
        changePassword.click();
        return new ChangePassword(driver);
    }
    public DeleteProfile clickOnDeleteProfileButton(){
        deleteProfile.click();
        return new DeleteProfile(driver);
    }
    public String getAdressData(String  numberAdress, String elem){
        return switch (elem) {
            case "city" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[1]/div[1]/p")).getText();
            case "region" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[1]/div[2]/p")).getText();
            case "district" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[1]/div[3]/p")).getText();
            case "street" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[2]/div[1]/p")).getText();
            case "house" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[2]/div[2]/p")).getText();
            case "corpus" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[2]/div[3]/p")).getText();
            case "entrance" -> driver.findElement(By.xpath("//form/div[" + numberAdress + 2 + "]/div/div[2]/div[4]/p")).getText();
            default -> null;
        };
    }





}

