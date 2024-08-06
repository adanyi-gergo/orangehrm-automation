import com.google.common.io.Files;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.RecruitmentPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTests {
    private final String USERNAME = "Admin";
    private final String PASSWORD = "admin123";
    private WebDriver driver;
    protected LoginPage loginPage;


    @BeforeEach
    public void setUp(){
        String chromeDriverPath = getClass().getClassLoader().getResource("chromedriver.exe").getPath();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-search-engine-choice-screen");

        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testCompanyVersionInAboutSection(){
        DashboardPage dashboard = loginPage.login(USERNAME, PASSWORD);
        String companyName = dashboard.getVersion();

        takeScreenshot("testCompanyVersionInAboutSection");
        assertEquals("OrangeHRM OS 5.7", companyName);
    }

    @Test
    public void testFoundRecordsNumberInRecruitment(){
        DashboardPage dashboard = loginPage.login(USERNAME, PASSWORD);
        RecruitmentPage recruitmentPage = dashboard.clickOnRecruitment();
        String foundRecords = recruitmentPage.getFoundRecordsNumber();

        takeScreenshot("testFoundRecordsNumberInRecruitment");
        assertEquals("(70) Records Found", foundRecords);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    private void takeScreenshot(String methodName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.move(srcFile, new File("src/main/resources/pictures/" +
                    getScreenshotFileName(methodName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getScreenshotFileName(String methodName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String dateTime = LocalDateTime.now().format(formatter);
        return String.format("%s_%s.png", methodName, dateTime);
    }
}
