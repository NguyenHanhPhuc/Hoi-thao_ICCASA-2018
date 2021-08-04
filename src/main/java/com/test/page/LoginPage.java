package com.test.page;

import com.test.page.object.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends CommonPage{
    LoginPageObject object = new LoginPageObject();
    public LoginPage(WebDriver _driver) {
        super(_driver);
        PageFactory.initElements(driver,object);
        Assert.assertTrue(waitForElement(object.BTN_LOGIN).isDisplayed());
    }

    public void enterUserName(String un){
        waitForElement(object.F_UID).sendKeys(un);
    }

    public void enterPassword(String pw){
        waitForElement(object.F_PASS).sendKeys(pw);
    }

    public HomePage clickSubmit(){
        waitForElement(object.BTN_LOGIN).click();
        return new HomePage(driver);
    }

    public void clickReset(){
        waitForElement(object.BTN_RESET).click();
    }
}
