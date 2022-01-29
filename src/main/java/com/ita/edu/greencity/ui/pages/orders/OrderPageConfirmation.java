package com.ita.edu.greencity.ui.pages.orders;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.payment.PrePaymentAlert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OrderPageConfirmation extends BasePage {

    @FindBy(how = How.XPATH, using = "//mat-step-header[@aria-posinset=1]/div[@class = 'mat-ripple mat-step-header-ripple']")
    private WebElement orderDetailsButton;
    @FindBy(how = How.XPATH, using = "//mat-step-header[@aria-posinset=2]/div[@class = 'mat-ripple mat-step-header-ripple']")
    private WebElement personalDataButton;

    @FindBy(how = How.XPATH, using = "//div[@class = 'container ng-star-inserted']/div/h3")
    private WebElement yourOrderTitle;
    @FindBy(how = How.XPATH, using = "//div[@class = 'w-100 d-flex justify-content-between mb-2']/label[contains(@class,'p-0')]")
    private List<WebElement> headersList;
    @FindBy(how = How.XPATH, using = "//div[@_ngcontent-ugo-c425]/p/span[2]/strong")
    private List<WebElement> totalSumList;
    @FindBy(how = How.XPATH, using = "//div[@_ngcontent-ugo-c425]/p/span[1]")
    private List<WebElement> amountsList;

    @FindBy(how = How.XPATH, using = "//select[contains(@class, 'payment-select shadow-none')]")
    private WebElement paymentChooser;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'btn-back ng-star-inserted')]")
    private WebElement backButton;
    @FindBy(how = How.XPATH, using = "//button[contains(@class,'cansel-button btn-main')]")
    private WebElement cancelButton;
    @FindBy(how = How.XPATH, using = "//button[@class = 'primary-global-button btn']")
    private WebElement orderButton;

    public OrderPageConfirmation(WebDriver driver) {
        super(driver);
    }

    public OrderDetailsPage clickOnOrderDetailsButton() {
        orderDetailsButton.click();
        return new OrderDetailsPage(driver);
    }

    public OrderPagePersonalData clickOnPersonalDataButton() {
        personalDataButton.click();
        return new OrderPagePersonalData(driver);
    }

    public String getTitle() {
        return yourOrderTitle.getText();
    }

    public String getNecessaryHeader(int index) {
        return headersList.get(index).getText();
    }

    public String chooseOneElementFromYourOrderTable(int row, int column) {
        return driver.findElement(By.xpath("//ul[@class = 'services-list w-100 p-0 m-0']/li[" + row + "]/span[" + column + "]"))
                .getText();
    }

    public String getTotalSumWithCurrency(int index) {
        return totalSumList.get(index).getText();
    }

    public String getNecessaryAmount(int index) {
        return amountsList.get(index).getText();
    }

    private OrderPageConfirmation clickOnPaymentChooser() {
        paymentChooser.click();
        return this;
    }

    public WebElement paymentOption(String value) {
        return driver.findElement(By.xpath("//select[contains(@class, 'payment-select shadow-none')]/option[@value = '" + value + "']"));
    }

    public OrderPageConfirmation choosePaymentMethod() {
        clickOnPaymentChooser();
        paymentOption("Fondy").click();
        return this;
    }

    public OrderPagePersonalData clickOnBackButton() {
        backButton.click();
        return new OrderPagePersonalData(driver);
    }

    public OrderSavingPopUp clickOnCancelButton() {
        cancelButton.click();
        return new OrderSavingPopUp(driver);
    }

    public PrePaymentAlert clickOnOrderButton() {
        orderButton.click();
        return new PrePaymentAlert(driver);
    }

}
