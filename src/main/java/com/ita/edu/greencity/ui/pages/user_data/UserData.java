package com.ita.edu.greencity.ui.pages.user_data;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
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

    @FindBy(how = How.XPATH, using = ".//*[contains(@formarrayname, 'address')]")
    private List<WebElement> allAdresses;

    public UserData(WebDriver driver) {
        super(driver);
    }

    public EditUserData clickOnEditDataButton()  {
        this.sleep(3000);
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
    private List<DisplayAddressContainer> getAddress() {
        List<DisplayAddressContainer> addressContainerList = new ArrayList<>();
        for (WebElement element : allAdresses) {
            addressContainerList.add(new DisplayAddressContainer(driver, element));
        }
        return addressContainerList;
    }

    public DisplayAddressContainer chooseAddressShow(String numberOfOrder) {
        this.sleep(3000);
        return getAddress()
                .stream()
                .filter(element -> element.getAddressNumberShow().equals(numberOfOrder))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }


}







