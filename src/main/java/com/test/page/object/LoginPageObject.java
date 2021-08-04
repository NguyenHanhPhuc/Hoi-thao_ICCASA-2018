package com.test.page.object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObject {
    @FindBy(name = "uid")
    public WebElement F_UID;

    @FindBy(name = "password")
    public WebElement F_PASS;

    @FindBy(name = "btnLogin")
    public WebElement BTN_LOGIN;

    @FindBy(name = "btnReset")
    public WebElement BTN_RESET;

}
