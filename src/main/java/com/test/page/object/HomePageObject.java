package com.test.page.object;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class HomePageObject {

    @FindBy(xpath = "//*[contains(text(),'Manger')]")
    public WebElement LB_MANAGER_ID;
/// Menu
    @FindBy(xpath = "//a[contains(text(),'Change Password')]")
    public WebElement MN_CHANGE_PASSWORD;

    @FindBy(xpath = "//a[contains(text(),'New Customer')]")
    public WebElement MN_NEW_CUSTOMER;

    @FindBy(xpath = "//a[contains(text(),'New Account')]")
    public WebElement MN_NEW_ACCOUNT;

    @FindBy(xpath = "//a[contains(text(),'Balance Enquiry')]")
    public WebElement MN_BALANCE_ENQUIRY;

    @FindBy(xpath = "//a[@href='DeleteCustomerInput.php']")
    public WebElement MN_DELETE_CUSTOMER;

    @FindBy(xpath = "//a[contains(text(),'Edit Account')]")
    public WebElement MN_EDIT_ACCOUNT;

    @FindBy(xpath = "//a[contains(text(),'Deposit')]")
    public WebElement MN_DEPOSIT;

    @FindBy(xpath = "//a[contains(text(),'Withdrawal')]")
    public WebElement MN_WITHDRAWAL;

    @FindBy(xpath = "//a[contains(text(),'Fund Transfer')]")
    public WebElement MN_FUND_TRANSFER;

    // Change password form
    @FindBy(xpath = "//input[@name='oldpassword']")
    public WebElement TF_CPF_OLD_PASS;

    @FindBy(xpath = "//input[@name='newpassword']")
    public WebElement TF_CPF_NEW_PASS;

    @FindBy(xpath = "//input[@name='confirmpassword']")
    public WebElement TF_CPF_CONFIRM_PASS;

    @FindBy(xpath = "//input[@name='sub']")
    public WebElement BTN_CPF_SUBMIT;

    @FindBy(xpath = "//input[@name='res']")
    public WebElement BTN_CPF_RESET;
// new user form
    @FindBy(name = "name")
    public List<WebElement> TF_NUF_NAME;

    @FindBy(name = "addr")
    public WebElement TF_NUF_ADDRESS;

    @FindBy(name = "city")
    public WebElement TF_NUF_CITY;

    @FindBy(name = "state")
    public List<WebElement> TF_NUF_STATE;

    @FindBy(name = "pinno")
    public List<WebElement> TF_NUF_PIN;

    @FindBy(name = "telephoneno")
    public List<WebElement> TF_NUF_PHONE;

    @FindBy(name = "emailid")
    public List<WebElement> TF_NUF_EMAIL;

//    @FindBy(xpath = "//input[@name='password']")
//    public WebElement TF_NUF_PASS;

    @FindBy(xpath = "//input[@name='password']")
    public List<WebElement> TF_NUF_PASS;

    @FindBy(xpath = "//input[@type='date']")
    public WebElement TF_NUF_DATE;

    //Customer detail
    @FindBy(xpath = "//td[contains(text(),'Customer ID')]/following-sibling::td[1]")
    public WebElement LB_CUSTOMER_ID;

    //New account form

    @FindBy(name = "cusid")
    public WebElement TF_CUSTOMER_ID;

    @FindBy(name = "selaccount")
    public WebElement SL_ACCOUNT_TYPE;

    @FindBy(name = "inideposit")
    public WebElement NF_INITIAL_DEPOSIT;

    @FindBy(name = "button2")
    public WebElement BTN_SUBMIT_NEW_ACCOUNT;

    //Account detail
    @FindBy(name = "accountno")
    public WebElement NF_ACCOUNT_EDIT;

    @FindBy(name = "AccSubmit")
    public WebElement BTN_SUBMIT_ACCOUNT_EDIT;

    @FindBy(name = "a_type")
    public WebElement SL_EDIT_ACCOUNT_TYPE;

    @FindBy(xpath = "//td[contains(text(),'Account ID')]/following-sibling::td[1]")
    public WebElement LB_ACCOUNT_ID;

    @FindBy(xpath = "//td[contains(text(),'Current Amount')]/following-sibling::td[1]")
    public WebElement LB_CURRENT_AMOUNT;

    @FindBy(xpath = "//td[contains(text(),'Account Type')]/following-sibling::td[1]")
    public WebElement LB_ACCOUNT_TYPE;

    @FindBy(xpath = "//td[contains(text(),'Balance')]/following-sibling::td[1]")
    public WebElement LB_BALANCE;

    @FindBy(name = "ammount")
    public WebElement NF_AMOUNT_DEPOSIT;

    @FindBy(name = "desc")
    public WebElement TF_DESCRIPTION;

    @FindBy(xpath = "//td[contains(text(),'Current Balance')]/following-sibling::td[1]")
    public WebElement LB_CURRENT_BALANCE;

    @FindBy(name = "payersaccount")
    public WebElement TF_PAYER_ACCOUNT;

    @FindBy(name = "payeeaccount")
    public WebElement TF_PAYEE_ACCOUNT;

    @FindBy(name = "ammount")
    public WebElement NF_FUND_AMOUNT;

    @FindBy(name = "AccSubmit")
    public WebElement BTN_SUBMIT_FUND_TRANSFER;

}
