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

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[1]")
    private WebElement name;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[2]")
    private WebElement surname;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[3]")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "(//p[@class = 'ng-star-inserted'])[4]")
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
    public String getTextFromNameField(){
        return name.getText();
    }
    public String getTextFromSurnameField(){
        return surname.getText();
    }
    public String getTextFromEmailField(){
        return email.getText();
    }
    public String getTextFromPhoneField(){
        return phone.getText();
    }
    public ChangePassword clickOnChangePasswordButton(){
        this.sleep(10000);
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
            addressContainerList.add(new DisplayAddressContainer(element));
        }
        return addressContainerList;
    }

    public DisplayAddressContainer chooseAddressShow(String number) {
        this.sleep(3000);
        return getAddress()
                .stream()
                .filter(element -> element.getAddressNumberShow().equals(number))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }


}







