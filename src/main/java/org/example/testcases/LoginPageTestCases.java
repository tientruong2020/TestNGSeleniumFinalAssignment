package org.example.testcases;

import org.example.pages.LoginPage;
import org.example.pages.SuccessfullyLoggedInPage;
import org.example.utils.ExcelUtil;
import org.testng.annotations.*;

import javax.lang.model.element.Name;
import java.io.IOException;

public class LoginPageTestCases extends TestSetup {
    private LoginPage loginPage;
    private SuccessfullyLoggedInPage loggedInPage;

    @DataProvider(name = "UnauthenticatedData")
    public Object[][] getUnauthenticatedData()throws IOException{
        return ExcelUtil.getDataFromExcel();
    }
    @DataProvider(name = "AuthenticatedData")
    public Object[][] createAuthenticatedData(){
        return new Object[][]{{"student","Password123"}};
    }

    @BeforeSuite
    public void setup(){
        loginPage = new LoginPage(this.getDriver(),this.getExplicitWait());
        loggedInPage = new SuccessfullyLoggedInPage(this.getDriver(),this.getExplicitWait());
    }

    @BeforeTest
    public void openWebPage(){
        loginPage.openWebPage();
        loginPage.verifyPageHeaderText();
    }
    @Test(dataProvider = "UnauthenticatedData")
    public void userLoginFail(String userName, String password, String expectedResult)throws IOException{
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();
        loginPage.verifyErrorMessage(expectedResult);
    }

    @Test(dataProvider = "AuthenticatedData")
    public void userLoginSuccessfully(String userName, String password){
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();
        loggedInPage.verifyUrl();
        loggedInPage.verifySuccessfullyLoggedInMessage();
        //verify logout button
        loggedInPage.clickLogoutButton();
        loginPage.verifyPageHeaderText();
    }

}
