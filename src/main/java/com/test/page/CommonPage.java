package com.test.page;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {
    protected WebDriver driver;
    public CommonPage(WebDriver _driver){
        this.driver = _driver;
    }

    private WebElement waitForPageToLoad(WebElement webElement, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            return wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            e.getMessage();
        }
        return webElement;
    }

    private WebElement waitForPageToLoad(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            return wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            e.getMessage();
        }
        return webElement;
    }

    protected WebElement waitForElement(WebElement webElement, int seconds) {
        try {
            return waitForPageToLoad(webElement, seconds);
        } catch (Exception e) {
            e.getMessage();
        }
        return webElement;
    }

    protected WebElement waitForElement(WebElement webElement) {
        try {
            return waitForPageToLoad(webElement);
        } catch (Exception e) {
            e.getMessage();
        }
        return webElement;
    }

    protected boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }


}
