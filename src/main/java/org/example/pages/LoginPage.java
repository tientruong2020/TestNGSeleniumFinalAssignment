package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
    private WebDriver webDriver;
    private WebDriverWait explicitWait;
    private String baseUrl = "https://practicetestautomation.com/practice-test-login/";
    private By userNameLocator = By.xpath("//input[@name='username']");
    private By passwordLocator = By.xpath("//input[@type='password']");
    private By submitButtonLocator = By.xpath("//button[@id='submit']");
    private By errorMsgLocator = By.xpath("//div[@id='error']");
    private By headerTextLocator = By.xpath("//section[@id='login']/h2");
    private final String headerText = "Test login";

    public LoginPage(WebDriver webDriver, WebDriverWait explicitWait) {
        this.webDriver = webDriver;
        this.explicitWait = explicitWait;
    }

    public void openWebPage(){
        this.webDriver.get(baseUrl);
    }

    public void enterUserName(String userName){
        WebElement userNameInputField = this.explicitWait.until
                (ExpectedConditions.elementToBeClickable
                        (this.webDriver.findElement(userNameLocator)));
        userNameInputField.sendKeys(userName);
    }
    public void enterPassword(String password){
        WebElement userNameInputField = this.explicitWait.until
                (ExpectedConditions.elementToBeClickable
                        (this.webDriver.findElement(passwordLocator)));
        userNameInputField.sendKeys(password);
    }

    public void clickSubmitButton(){
        WebElement submitButton = this.explicitWait.until
                (ExpectedConditions.elementToBeClickable
                        (this.webDriver.findElement(submitButtonLocator)));
        submitButton.click();
    }

    public void verifyErrorMessage(String errorMessage){
        WebElement errMsg = this.explicitWait.until
                (ExpectedConditions.elementToBeClickable
                        (this.webDriver.findElement(errorMsgLocator)));
        Assert.assertEquals(errMsg.getText(),errorMessage);
    }

    public void verifyPageHeaderText(){
        WebElement headerTextElement = this.explicitWait.until(ExpectedConditions.visibilityOf
                (this.webDriver.findElement(headerTextLocator)));
        String actualHeaderText = headerTextElement.getText();
        Assert.assertEquals(actualHeaderText, headerText);
    }

}
