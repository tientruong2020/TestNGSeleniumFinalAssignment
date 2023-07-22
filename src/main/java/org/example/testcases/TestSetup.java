package org.example.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class TestSetup {
    private WebDriver driver;
    private WebDriverWait explicitWait;

    @BeforeSuite
    public void setupTestDriver() {
        String webDriverPath = System.getProperty("user.dir") + "\\src\\main\\resources\\web_driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();
        this.driver = new ChromeDriver(service);
        this.explicitWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        this.driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() {
        this.driver.close();
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriverWait getExplicitWait() {
        return this.explicitWait;
    }
}
