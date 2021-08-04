package com.test.page.object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPageObject {

    @FindBy(name = "emailid")
    public WebElement TF_EMAIL;

    @FindBy(name = "btnLogin")
    public WebElement BTN_SUBMIT;

    @FindBy(xpath = "//td[contains(text(),'ID')]/following-sibling::td[1]")
    public WebElement F_UN;

    @FindBy(xpath = "//td[contains(text(),'Password')]/following-sibling::td[1]")
    public WebElement F_PW;



}
