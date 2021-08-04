package com.test.page;

import com.test.page.object.RegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends CommonPage {
    private RegisterPageObject object = new RegisterPageObject();
    public RegisterPage(WebDriver _driver){
        super(_driver);
        PageFactory.initElements(driver,object);
    }

    public void enterEmail(String email){
        waitForElement(object.TF_EMAIL).sendKeys(email);
    }

    public void clickSubmit(){
        waitForElement(object.BTN_SUBMIT).click();
    }

    public String getUserName(){
        return waitForElement(object.F_UN).getText();
    }

    public String getPassWord(){
        return waitForElement(object.F_PW).getText();
    }
}
