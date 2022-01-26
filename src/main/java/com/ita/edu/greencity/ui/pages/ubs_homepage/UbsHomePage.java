package com.ita.edu.greencity.ui.pages.ubs_homepage;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Properties;

public class UbsHomePage extends BasePage {


    @FindBy(how = How.XPATH, using = "//*/header/div[@class='header-left']/*/img[@class='ubs-image']")
    private WebElement imgArmoredTrack;

    @FindBy(how = How.XPATH, using = "//*/h1")
    private WebElement headerMainText;

    @FindBy(how = How.XPATH, using = "//*/header/div[@class='header-right']/*/p")
    private WebElement headerText;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs/app-ubs-main-page/div/header/div[2]/div/button")
    private WebElement headerButtonOrderCourier;

    @FindBy(how = How.XPATH, using = "//h1")
    private WebElement h1Text;


    @FindBy(how = How.XPATH, using = "//*[@class='section-header']")
    private WebElement middleText;

    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-ubs/app-ubs-main-page/div/header/div[2]/div/button")
    private WebElement orderCourier;

    @FindBy(how = How.XPATH, using = "//*[@class='ubs-polygon']")
    private WebElement arrImg;

    public UbsHomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(how = How.XPATH, using = "//*/section/img[@class='ubs-image']")
    private WebElement selectionImg;

    @FindBy(how = How.XPATH, using = "//*/section/div[@class='header-right']/*/h2")
    private WebElement sectionMainText;

    //_________________________________________________________
    @FindBy(how = How.XPATH, using = "//div[@class='header-right']//ul//li[4]")
    private WebElement section4MiddleText;


    @FindBy(how = How.XPATH, using = "//*[@id='main-content']/button")
    private WebElement secondButtonOrderCourier;

    @FindBy(how = How.XPATH, using = "//div[@class='main-content']/*/button")
    private WebElement footerButton;



    //_______________________________________________________________________
    public String getTitleH1Text() {
        return h1Text.getText();
    }

    public SignInComponent pressOrderCourier() {
        orderCourier.click();
        headerButtonOrderCourier.click();
        return new SignInComponent(driver);
    }

    public String clickImgArmoredTrack() {
        imgArmoredTrack.getTagName();
        return null;
    }

    public String getTextHeaderMainText() {
        return headerMainText.getText();
    }

    public UbsHomePage getHeaderText() {
        headerText.getText();
        return this;
    }

    public UbsHomePage getTypeOfHeaderButtonOrderCourier() {
        headerButtonOrderCourier.getText();
        return this;
    }


    public void clickHeaderButtonOrderCourier() {
        headerButtonOrderCourier.click();
    }

    public String getMiddleText() {
        return middleText.getText();

    }

    public UbsHomePage clickArrImg() {
        arrImg.getTagName();
        return this;
    }

    public void clickSelectionImg() {
        selectionImg.click();
    }

    public UbsHomePage getSectionMainText() {
        sectionMainText.click();
        return this;
    }

    public String getSectionFirstMiddleText() {
        return section4MiddleText.getText();
    }
}