package testcase;


import com.test.constant.AccountTypeConstant;
import com.test.page.HomePage;
import com.test.page.LoginPage;
import com.test.page.RegisterPage;
import com.test.until.DataStorage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.io.IOException;

public class Testsuite extends Core{


    public Testsuite() throws IOException {
    }


    @Test(priority = 1,description = "get username and password")
    public void tc01(){
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterEmail("test@test.com");
        registerPage.clickSubmit();

        un= registerPage.getUserName();
        pw= registerPage.getPassWord();
    }

    @Test(priority = 2,description = "Signin")
    public void tc02(){
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
    }

    @Test(priority = 2, description = "Change password")
    public void tc03(){
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickChangePassword();

        homePage.enterPassword(pw,pw);
        homePage.clickSubmitChangePassword();
    }

    @Test(priority = 2, description = "New customer")
    public void tc04() throws IOException {
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickNewCustomerMenu();
        homePage.enterNewUserInfo();
        homePage.clickSubmitNewCustomer();
        homePage.saveCustomerID();
    }

    @Test(priority = 2, description = "New account")
    public void tc05() throws IOException, ClassNotFoundException {
        WebDriver driver = drivers.get();
        String amount = "10000";
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickNewAccountMenu();
        String usid =DataStorage.getData(1).toArray()[0].toString();
        homePage.enterCustomerID(usid);
        homePage.selectAccountType(AccountTypeConstant.SAVINGS);
        homePage.enterInitialDeposit(amount);
        homePage.clickSubmitNewAccount();
        homePage.checkAccountInfo(amount);
        homePage.saveAccountID();
    }

    @Test(priority = 2, description = "Edit account")
    public void tc06() throws IOException, ClassNotFoundException {
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickEditAccountMenu();
        String accid =DataStorage.getData(2).toArray()[0].toString();
        homePage.enterAccountID(accid);
        homePage.clickSubmitEditForm();
        String newType = homePage.changeAccountType();
        homePage.clickSubmitEditForm();
        homePage.checkDetailInfoAfterEdit(newType);
    }

    @Test(priority = 2, description = "Deposit to an account")
    public void tc07() throws IOException, ClassNotFoundException {
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickBalanceEnquiryMenu();
        String accid =DataStorage.getData(2).toArray()[0].toString();
        homePage.enterAccountID(accid);
        homePage.clickSubmitEditForm();
        int currentAmount = homePage.saveCurrentAmount();
        homePage.clickDepositMenu();
        homePage.enterAccountID(accid);
        int deposit = 1200;
        homePage.enterAmount(deposit);
        homePage.enterDescription("deposit");
        homePage.clickSubmitEditForm();
        homePage.checkTotalAmount(currentAmount,deposit);
    }

    @Test(priority = 2, description = "Withdraw from an account")
    public void tc08() throws IOException, ClassNotFoundException {
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickBalanceEnquiryMenu();
        String accid =DataStorage.getData(2).toArray()[0].toString();
        homePage.enterAccountID(accid);
        homePage.clickSubmitEditForm();
        int currentAmount = homePage.saveCurrentAmount();
        homePage.clickWithdrawalMenu();
        homePage.enterAccountID(accid);
        int withdraw = 1200;
        homePage.enterAmount(withdraw);
        homePage.enterDescription("withdraw");
        homePage.clickSubmitEditForm();
        homePage.checkRemainingAmount(currentAmount,withdraw);
    }

    @Test(priority = 2, description = "Fund Transfer")
    public void tc09() throws IOException, ClassNotFoundException {
        WebDriver driver = drivers.get();
        String payerAccID =DataStorage.getData(2).toArray()[0].toString();
        String payeeAccID =DataStorage.getData(2).toArray()[1].toString();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);

        homePage.clickBalanceEnquiryMenu();
        homePage.enterAccountID(payerAccID);
        homePage.clickSubmitEditForm();
        int per01 = homePage.saveCurrentAmount();

        homePage.clickBalanceEnquiryMenu();
        homePage.enterAccountID(payeeAccID);
        homePage.clickSubmitEditForm();
        int pee01 = homePage.saveCurrentAmount();

        int amount = 10;
        String desc = "fund transfer";
        homePage.clickFundTransferMenu();
        homePage.enterAccountID(payerAccID,payeeAccID);
        homePage.enterAmountAndDesc(amount,desc);
        homePage.clickSubmitTransfer();

        homePage.clickBalanceEnquiryMenu();
        homePage.enterAccountID(payerAccID);
        homePage.clickSubmitEditForm();
        int per02 = homePage.saveCurrentAmount();

        homePage.clickBalanceEnquiryMenu();
        homePage.enterAccountID(payeeAccID);
        homePage.clickSubmitEditForm();
        int pee02 = homePage.saveCurrentAmount();

        homePage.checkAccountBalance(per01,pee01,per02,pee02,amount);


    }

    @Test(dependsOnMethods = "tc01", description = "Delete an account")
    public void tc10(){
        WebDriver driver = drivers.get();
        driver.get("http://demo.guru99.com/v4/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(un);
        loginPage.enterPassword(pw);
        HomePage homePage = loginPage.clickSubmit();
        homePage.checkManagerID(un);
        homePage.clickDeleteCustomerMenu(); //49461
        homePage.enterCustomerID("49461");
        homePage.clickSubmitEditForm();
        homePage.clickAcceptDeleteCustomer();
        homePage.checkIfDeleteSuccess();
    }

}
