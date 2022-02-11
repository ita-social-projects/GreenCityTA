package com.ita.edu.greencity.ui.pages.employees;

import com.ita.edu.greencity.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Employees extends BasePage {

    public Employees(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs/app-header/header/div/div/div/div/ul/ul[2]/li[1]")
    private WebElement adminPopMenu;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs/app-header/header/div/div/div/div/ul/ul[2]/li[5]/a")
    private WebElement buttonUbsAdmin;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/div/mat-drawer-container/mat-drawer/div/ul/li[4]")
    private WebElement buttonEmployees;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/div/mat-drawer-container/mat-drawer-content/div/app-ubs-admin-employee/div/div[1]/div[2]/button")
    private WebElement buttonAddEmployees;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs-admin/app-ubs-admin-sidebar/app-ubs-base-sidebar/div/mat-drawer-container/mat-drawer-content/div/app-ubs-admin-employee/div/div[2]/app-ubs-admin-employee-table/div/mat-form-field/div/div[1]/div/input")
    private WebElement inputArr;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='firstName']")
    private WebElement inputName;

    @FindBy(how = How.XPATH, using = "//input[@formcontrolname='lastName']")
    private WebElement inputSurname;

    @FindBy(how = How.XPATH, using = "//input[@class='ng-dirty ng-valid ng-touched']")
    private WebElement inputPhone;



    @Step("click on pop-up menu")
    public void clickAdminPopMenu(){
        adminPopMenu.click();
    }

    @Step("click on UBS-Admin button")
    public void clickButtonUbsAdmin(){
        buttonUbsAdmin.click();
    }

    @Step("press on Employees menu")
    public void clickEmployyesButton(){
        buttonEmployees.click();
    }

    @Step("check if button on display")
    public boolean checkIfButtonAddEmplOnDipl(){
        return buttonAddEmployees.isDisplayed();
    }

    @Step("check if input on display")
    public boolean checkIfInputOnDisplay(){
        return inputArr.isDisplayed();
    }


    @Step("input some string to InputArr")
    public Employees sendKeysInputArr(String inputText){
        inputArr.clear();
        inputArr.sendKeys(inputText, Keys.ENTER);
        return this;
    }

    @Step("press on Add Employee")
    public void pressButtonAddEmployee(){
        buttonAddEmployees.click();
    }


    @Step("input value to nameArr")
    public Employees sendKeysNameArr(String inputText){
        inputName.clear();
        inputName.sendKeys(inputText, Keys.ENTER);
        return this;
    }

    @Step("input value to surnameArr")
    public Employees sendKeysSurnameArr(String inputText){
        inputSurname.clear();
        inputSurname.sendKeys(inputText, Keys.ENTER);
        return this;
    }

    @Step("input value to phoneArr")
    public Employees sendKeysPhoneArr(String inputNum){
        inputPhone.clear();
        inputPhone.sendKeys(inputNum, Keys.ENTER);
        return this;
    }

}
