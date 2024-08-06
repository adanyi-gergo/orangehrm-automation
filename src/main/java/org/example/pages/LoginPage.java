package org.example.pages;

import org.example.util.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WaitHelper waitHelper;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver, Duration.ofSeconds(10));
    }

    public DashboardPage login(String username, String password){
        waitHelper.waitForVisibility(usernameField);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

        return new DashboardPage(driver);
    }
}
