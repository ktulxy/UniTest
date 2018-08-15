import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CreateContacts {

    private static WebDriver driver;

    @BeforeClass
    public static void logIn() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://cp.unisender.com/ru/v5/signin");
        WebElement loginField = driver.findElement(By.id("username"));
        loginField.sendKeys("omudritsky@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123666456");
        driver.findElement(By.cssSelector("button.login__boxButton.login__boxButton_blue.login__boxButton_fullWidth")).click();
        driver.findElement(By.xpath("//div[2]/div/button/span")).click();
        driver.findElement(By.cssSelector("span.menuItem__menuSubItemText")).click();
        driver.findElement(By.xpath("//a[contains(text(),'MyContacts')]")).click();
    }

    @Test
    public void createContacts() throws InterruptedException {
        WebElement count1 = driver.findElement(By.xpath("//tbody[@id='listCountsContainer']/tr/td/span/span"));
        String count = count1.getText();
        int a = Integer.parseInt(count);

        while (a < 11) {
            Thread.sleep(1000);
                driver.findElement(By.id("btnAddSubscriber")).click();
                WebElement user = driver.findElement(By.id("subscriber_contacts_address_contact"));
                Thread.sleep(2000);
                user.sendKeys("joinm" + a + "@bigmir.net");
                WebElement phone = driver.findElement(By.id("subscriber_contacts_phone_contact"));
                phone.sendKeys("+38098765432" + a);
                WebElement name = driver.findElement(By.id("subscriber_fields_6515377_value"));
                name.sendKeys("Oleg");
                driver.findElement(By.id("saveSubscriber")).click();
                System.out.println("Нужно еще, есть только " + a);
                a++;
                }
        driver.quit();
    }
}
