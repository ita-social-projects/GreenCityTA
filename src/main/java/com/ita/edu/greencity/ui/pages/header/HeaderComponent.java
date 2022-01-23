package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HeaderComponent extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[@class='header_logo active-link']")
    private WebElement ubsLogo;

    @FindBy(how = How.XPATH, using = "//*[@class='header_navigation-menu-left']/*/li[1]/a")
    private WebElement aboutUs;

    @FindBy(how = How.XPATH, using = "//*[@class='header_navigation-menu-left']/*/li[2]/a")
    private WebElement sortingRules;

    @FindBy(how = How.XPATH, using = "//*[@class='header_navigation-menu-left']/*/li[3]/a")
    private WebElement ecoShop;

    @FindBy(how = How.XPATH, using = "//*[@class='header_navigation-menu-left']/*/li[4]/a")
    private WebElement greenCity;

    @FindBy(how = How.XPATH, using = "//*[@class='header_navigation-menu-right']/*/li[1]")
    private WebElement search;

    @FindBy(how = How.XPATH, using = "//*[@class='header_lang-switcher-wrp header_navigation-menu-right-list']")
    private WebElement languageSwitcher;

    @FindBy(how = How.XPATH, using = "//ul[@class='add-shadow header_lang-switcher-wrp header_navigation-menu-right-list']/li")
    private List<WebElement> languagesList;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void clickUbsLogo() {
        ubsLogo.click();
    }

    public void clickAboutUs() {
        aboutUs.click();
    }

    public void clickSortingRules() {
        sortingRules.click();
    }

    public void clickEcoShop() {
        ecoShop.click();
    }

    public void clickGreenCity() {
        greenCity.click();
    }

    public void clickSearch() {
        search.click();
    }

    public void clickLanguageSwitcher() {
        languageSwitcher.click();
    }
}
