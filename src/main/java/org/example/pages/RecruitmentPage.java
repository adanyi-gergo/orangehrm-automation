package org.example.pages;

import org.example.util.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class RecruitmentPage {
    private WebDriver driver;
    private WaitHelper waitHelper;

    @FindBy(xpath = "//div[@class='orangehrm-header-container']/following-sibling::div[1]//span")
    private WebElement recordsFound;

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver, Duration.ofSeconds(10));
    }

    public String getFoundRecordsNumber(){
        waitHelper.waitForVisibility(recordsFound);
        return recordsFound.getText();
    }

}
