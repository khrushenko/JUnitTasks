package wikipedia;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Test1 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    public final String SCREENSHOT_FOLDER = "/home/master/Screenshot/";

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://uk.wikipedia.org/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testWiki1() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[4]/div/table[2]/tbody/tr/td[3]/div/div/div[1]/dl/dd/a[2]")).click();
        getScreenshot(SCREENSHOT_FOLDER + "test_1_1.jpg");
        driver.get(baseUrl);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void getScreenshot(String path) throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(path));
    }

}