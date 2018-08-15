

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HeadTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://cp.unisender.com/ru/v5/signin");
    }
    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.id("username"));
        loginField.sendKeys("omudritsky@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123666456");
        driver.findElement(By.cssSelector("button.login__boxButton.login__boxButton_blue.login__boxButton_fullWidth")).click();
       WebElement verify = driver.findElement(By.xpath("//button/div/div/span"));
       String verifyMail = verify.getText();
       Assert.assertEquals("omudritsky@gmail.com",verifyMail);
       verify.click();
    }
    @AfterClass
    public static void exit(){
        driver.findElement(By.xpath("//div[2]/div/a[7]/span[2]")).click();
        driver.quit();
    }


    }


