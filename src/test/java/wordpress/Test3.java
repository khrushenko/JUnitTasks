package wordpress;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test3 {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    private String login;
    private String password;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://demosite.center/wordpress/wp-login.php";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testWordpress3() throws Exception {
        driver.get(baseUrl);
        readFile();
        driver.findElement(By.id("user_login")).clear();
        driver.findElement(By.id("user_login")).sendKeys(login);
        driver.findElement(By.id("user_pass")).clear();
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        if (driver.findElements(By.cssSelector("#wp-admin-bar-my-account")).size() != 0) {
            try {
                assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Howdy, admin[\\s\\S]*$"));
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }

            driver.findElement(By.linkText("Posts")).click();
            driver.findElement(By.cssSelector("a.add-new-h2")).click();
            driver.findElement(By.id("title")).clear();
            driver.findElement(By.id("title")).sendKeys("Selenium Demo Post");
            driver.findElement(By.id("publish")).click();
            try {
                assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Post published[\\s\\S]*$"));
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }
        } else System.out.println("Login is not successful ");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    private void readFile() throws IOException {
        File file = new File("/home/master/file.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        login = reader.readLine();
        password = reader.readLine();
    }
}