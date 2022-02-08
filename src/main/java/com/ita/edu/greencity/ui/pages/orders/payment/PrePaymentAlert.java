package com.ita.edu.greencity.ui.pages.orders.payment;

import com.ita.edu.greencity.ui.pages.BasePage;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrePaymentAlert extends BasePage {

    protected WebDriverWait wait;

    public PrePaymentAlert(WebDriver driver) {
        super(driver);
    }

    private void acceptAlertMessage() {
        driver.switchTo().alert().accept();
    }

    private void dismissAlertMessage() {
        driver.switchTo().alert().dismiss();
    }

    public PaymentByFondyPage acceptAlert() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.alertIsPresent());
        acceptAlertMessage();
        return new PaymentByFondyPage(driver);
    }

    public OrderPageConfirmation dismissAlert() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.alertIsPresent());
        dismissAlertMessage();
        return new OrderPageConfirmation(driver);
    }
}

