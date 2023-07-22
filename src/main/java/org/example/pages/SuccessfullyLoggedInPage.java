package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SuccessfullyLoggedInPage {
    private WebDriver webDriver;
    private WebDriverWait explicitWait;
    private final String successfullyLoggedInMessage ="Logged In Successfully";
    private final String successfullyLoggedInPageUrl = "https://practicetestautomation.com/logged-in-successfully/";

    private By logoutButtonLocator = By.xpath("//a[text()='Log out']");
    private By loggedInMessageLocator = By.xpath("//h1[@class='post-title']");


    public SuccessfullyLoggedInPage(WebDriver webDriver, WebDriverWait explicitWait) {
        this.webDriver = webDriver;
        this.explicitWait = explicitWait;
    }

    public void verifyUrl(){
        String url = this.webDriver.getCurrentUrl();
        Assert.assertEquals(url,successfullyLoggedInPageUrl);
    }

    public void verifySuccessfullyLoggedInMessage(){
        WebElement loggedInMsg = this.explicitWait.until(ExpectedConditions.visibilityOf
                (this.webDriver.findElement(loggedInMessageLocator)));
        String actualMsg = loggedInMsg.getText();
        Assert.assertEquals(actualMsg,successfullyLoggedInMessage);
    }

    public void clickLogoutButton(){
        WebElement logoutButton = this.webDriver.findElement(logoutButtonLocator);
        logoutButton.click();
    }
}
