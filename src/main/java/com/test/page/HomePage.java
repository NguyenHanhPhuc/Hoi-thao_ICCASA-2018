package com.test.page;

import com.test.constant.AccountTypeConstant;
import com.test.page.object.HomePageObject;
import com.test.until.DataStorage;
import com.test.until.DateTimeUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class HomePage extends CommonPage{
    HomePageObject hpo = new HomePageObject();
    public HomePage(WebDriver _driver) {
        super(_driver);
        PageFactory.initElements(driver,hpo);
        Assert.assertTrue(waitForElement(hpo.LB_MANAGER_ID).isDisplayed());
    }

    public void checkManagerID(String un){
        String manager_id = hpo.LB_MANAGER_ID.getText();
        manager_id = manager_id.substring(12);
        Assert.assertEquals(manager_id,un);
    }

    public void clickChangePassword(){
        waitForElement(hpo.MN_CHANGE_PASSWORD).click();
    }

    public void clickNewCustomerMenu(){
        waitForElement(hpo.MN_NEW_CUSTOMER).click();
    }

    public void enterPassword(String old,String new_one){
        waitForElement(hpo.TF_CPF_OLD_PASS).sendKeys(old);
        waitForElement(hpo.TF_CPF_NEW_PASS).sendKeys(new_one);
        waitForElement(hpo.TF_CPF_CONFIRM_PASS).sendKeys(new_one);
    }

    public void clickSubmitChangePassword(){
        waitForElement(hpo.BTN_CPF_SUBMIT).click();
        new WebDriverWait(driver, 10)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());

        Alert al = driver.switchTo().alert();
        Assert.assertEquals(al.getText(),"Password is Changed");
        al.accept();
        new LoginPage(driver);
    }

    public void enterNewUserInfo(){
        String currentTime= DateTimeUtils.getCurrentDateTime("ddMMHHmmss");
        waitForElement(hpo.TF_NUF_ADDRESS).sendKeys("120A Los Angeles");
        waitForElement(hpo.TF_NUF_CITY).sendKeys("Los Angeles");
        waitForElement(hpo.TF_NUF_DATE).sendKeys("15/11/1996");
        waitForElement(hpo.TF_NUF_PASS.get(0)).sendKeys("abc123!");
        waitForElement(hpo.TF_NUF_EMAIL.get(0)).sendKeys("abc"+currentTime+"@yahoo.com");
        waitForElement(hpo.TF_NUF_PHONE.get(0)).sendKeys("09696969369");
        waitForElement(hpo.TF_NUF_PIN.get(0)).sendKeys("123456");
        waitForElement(hpo.TF_NUF_STATE.get(0)).sendKeys("lala");
        waitForElement(hpo.TF_NUF_NAME.get(0)).sendKeys("abc");
    }

    public void clickSubmitNewCustomer(){
        waitForElement(hpo.BTN_CPF_SUBMIT).click();
    }

    public void saveCustomerID() throws IOException {
        int id = Integer.parseInt(waitForElement(hpo.LB_CUSTOMER_ID).getText());
        DataStorage.setData(id,1);
    }

    public void clickNewAccountMenu(){
        waitForElement(hpo.MN_NEW_ACCOUNT).click();
    }

    public void enterCustomerID(String cusid){
        waitForElement(hpo.TF_CUSTOMER_ID).sendKeys(cusid);
    }

    public void selectAccountType(String type){
        Select accoutType = new Select(hpo.SL_ACCOUNT_TYPE);
        accoutType.selectByVisibleText(type);
    }

    public void enterInitialDeposit(String initial_amount){
        waitForElement(hpo.NF_INITIAL_DEPOSIT).sendKeys(initial_amount);
    }

    public void clickSubmitNewAccount(){
        waitForElement(hpo.BTN_SUBMIT_NEW_ACCOUNT).click();
    }

    public void checkAccountInfo(String amount1){
        String amount2 =waitForElement(hpo.LB_CURRENT_AMOUNT).getText();
        Assert.assertEquals(amount1,amount2);
    }

    public void saveAccountID() throws IOException {
        String accountID = waitForElement(hpo.LB_ACCOUNT_ID).getText();
        DataStorage.setData(Integer.parseInt(accountID),2);
    }

    public void clickEditAccountMenu(){
        waitForElement(hpo.MN_EDIT_ACCOUNT).click();
    }

    public void enterAccountID(String accountID){
        waitForElement(hpo.NF_ACCOUNT_EDIT).sendKeys(accountID);
    }

    public String changeAccountType(){
        Select select = new Select(hpo.SL_EDIT_ACCOUNT_TYPE);
        String currentType = select.getFirstSelectedOption().getText();
        if (currentType.equals(AccountTypeConstant.SAVINGS)){
            select.selectByVisibleText(AccountTypeConstant.CURRENT);
            return AccountTypeConstant.CURRENT;
        }
        else {
            select.selectByVisibleText(AccountTypeConstant.SAVINGS);
            return AccountTypeConstant.SAVINGS;
        }
    }

    public void clickSubmitEditForm(){
        waitForElement(hpo.BTN_SUBMIT_ACCOUNT_EDIT).click();
    }

    public void checkDetailInfoAfterEdit(String expected){
        String actual = waitForElement(hpo.LB_ACCOUNT_TYPE).getText();
        Assert.assertEquals(expected,actual);
    }

    public void clickBalanceEnquiryMenu(){
        waitForElement(hpo.MN_BALANCE_ENQUIRY).click();
    }

    public void clickDeleteCustomerMenu(){
        waitForElement(hpo.MN_DELETE_CUSTOMER).click();
    }

    public int saveCurrentAmount(){
        String current = waitForElement(hpo.LB_BALANCE).getText();
        return Integer.parseInt(current);
    }

    public void clickDepositMenu(){
        waitForElement(hpo.MN_DEPOSIT).click();
    }

    public void enterAmount(int amount){
        waitForElement(hpo.NF_AMOUNT_DEPOSIT).sendKeys(String.valueOf(amount));
    }

    public void enterDescription(String description){
        waitForElement(hpo.TF_DESCRIPTION).sendKeys(description);
    }

    public void checkTotalAmount(int curr,int depo){
        String actualamount = waitForElement(hpo.LB_CURRENT_BALANCE).getText();
        int actual= Integer.parseInt(actualamount);
        Assert.assertEquals(curr+depo,actual);
    }

    public void clickWithdrawalMenu(){
        waitForElement(hpo.MN_WITHDRAWAL).click();
    }

    public void checkRemainingAmount(int curr,int depo){
        String actualamount = waitForElement(hpo.LB_CURRENT_BALANCE).getText();
        int actual= Integer.parseInt(actualamount);
        Assert.assertEquals(curr-depo,actual);
    }

    public void clickFundTransferMenu(){
        waitForElement(hpo.MN_FUND_TRANSFER).click();
    }

    public void enterAccountID(String payer,String payee){
        waitForElement(hpo.TF_PAYER_ACCOUNT).sendKeys(payer);
        waitForElement(hpo.TF_PAYEE_ACCOUNT).sendKeys(payee);
    }

    public void enterAmountAndDesc(int amount,String desc){
        waitForElement(hpo.NF_FUND_AMOUNT).sendKeys(String.valueOf(amount));
        waitForElement(hpo.TF_DESCRIPTION).sendKeys(desc);
    }

    public void clickSubmitTransfer(){
        waitForElement(hpo.BTN_SUBMIT_FUND_TRANSFER).click();
    }

    public void checkAccountBalance(int per01,int pee01,int per02, int pee02, int amount){
        Assert.assertEquals(per02,per01-amount);
        Assert.assertEquals(pee02,pee01+amount);
    }

    public void clickAcceptDeleteCustomer(){
        driver.switchTo().alert().accept();
    }

    public void checkIfDeleteSuccess(){
        String text="";
        if(isAlertPresent()){
            text = driver.switchTo().alert().getText();
        }
        Assert.assertEquals(text,"Customer deleted Successfully");
    }


}
