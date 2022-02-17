package com.ita.edu.greencity.ui.pages.header;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.header.external_sites.EcoShop;
import com.ita.edu.greencity.ui.pages.header.external_sites.GreenCity;
import com.ita.edu.greencity.ui.pages.header.external_sites.SortingRules;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Step;
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

    @FindBy(how = How.XPATH, using = "//*[@class='header_lang-switcher-wrp header_navigation-menu-right-list']")
    private WebElement languageSwitcher;

    @FindBy(how = How.XPATH, using = "//ul[@class='add-shadow header_lang-switcher-wrp header_navigation-menu-right-list']/li")
    private List<WebElement> languagesList;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click on UBS logo button")
    public UbsHomePage clickUbsLogo() {
        ubsLogo.click();
        return new UbsHomePage(driver);
    }

    @Step("Click on About us button")
    public HeaderComponent clickAboutUs() {
        aboutUs.click();
        return this;
    }

    @Step("Click on Sorting Rules button")
    public SortingRules clickSortingRules() {
        sleep(1000);
        sortingRules.click();
        return new SortingRules(driver);
    }

    @Step("Click on EcoShop button")
    public EcoShop clickEcoShop() {
        sleep(1000);
        ecoShop.click();
        return new EcoShop(driver);
    }

    @Step("Click on GreenCity button")
    public GreenCity clickGreenCity() {
        sleep(1000);
        greenCity.click();
        return new GreenCity(driver);
    }

    @Step("Get language from switcher")
    public String getLanguage() {
        return languageSwitcher.getText();
    }

    @Step("Click on language switcher")
    public HeaderComponent clickLanguageSwitcher() {
        languageSwitcher.click();
        return this;
    }

    public List<WebElement> getLanguagesList() {
        return languagesList;
    }

    @Step("Choose {language} language")
    public HeaderComponent languageChoose(String language) {
        for (WebElement current : getLanguagesList()) {
            if (current != null & current.getAttribute("aria-label").equalsIgnoreCase(language)) {
                current.click();
                break;
            }
        }
        return this;
    }

    public String getAboutUsText() {
        return aboutUs.getText();
    }
}
