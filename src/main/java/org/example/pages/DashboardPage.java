package org.example.pages;

import org.example.util.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WaitHelper waitHelper;

    @FindBy(className = "oxd-userdropdown-tab")
    private WebElement userDropdownTab;
    @FindBy(xpath = "//a[text()='About']")
    private WebElement userDropdownAboutOption;
    @FindBy(xpath = "//p[normalize-space()='Version:']/parent::div/following-sibling::div/p")
    private WebElement version;
    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentPage;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver, Duration.ofSeconds(10));
    }

    private void clickOnUserDropdown(){
        waitHelper.waitForVisibility(userDropdownTab);
        userDropdownTab.click();
    }

    private void clickOnUserDropdownAboutOption(){
        waitHelper.waitForVisibility(userDropdownAboutOption);
        userDropdownAboutOption.click();
    }

    public String getVersion(){
        clickOnUserDropdown();
        clickOnUserDropdownAboutOption();
        waitHelper.waitForVisibility(version);

        return version.getText();
    }

    public RecruitmentPage clickOnRecruitment(){
        waitHelper.waitForVisibility(recruitmentPage);
        recruitmentPage.click();

        return new RecruitmentPage(driver);
    }

}
