import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CopyToList {

    private static WebDriver driver;

    @BeforeClass
    public static void logIn(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://cp.unisender.com/ru/v5/signin");
        WebElement loginField = driver.findElement(By.id("username"));
        loginField.sendKeys("omudritsky@gmail.com");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("123666456");
        driver.findElement(By.cssSelector("button.login__boxButton.login__boxButton_blue.login__boxButton_fullWidth")).click();
        driver.findElement(By.xpath("//div[2]/div/button/span")).click();
        driver.findElement(By.cssSelector("span.menuItem__menuSubItemText")).click();
    }
    @Test
    public void copyContact() {
        driver.findElement(By.xpath("//tbody[@id='contactListsContainer']/tr[2]/td/a")).click();
        WebElement count2 = driver.findElement(By.xpath("//tbody[@id='listCountsContainer']/tr/td/span/span"));
        String count0 = count2.getText();
        driver.findElement(By.xpath("//label[@id='lblAllPersons']/i")).click();
        driver.findElement(By.xpath("//span[@id='drop-copy']/u")).click();
        driver.findElement(By.xpath("//div/div[2]/div/div/div/ul/li[2]/span")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
        WebElement count1 = driver.findElement(By.xpath("//tbody[@id='listCountsContainer']/tr/td/span/span"));
        String count = count1.getText();
        Assert.assertEquals(count0,count);
        System.out.println("Скопировано " + count + " контактов");
    }
    @AfterClass
    public static void clean() throws InterruptedException {
        driver.findElement(By.id("lnkListHomepage")).click();
        driver.findElement(By.xpath("//a[contains(text(),'ImportContacts')]")).click();
        driver.findElement(By.xpath("//label[@id='lblAllPersons']/i")).click();
        driver.findElement(By.xpath("//span[@id='drop-more']/u")).click();
        driver.findElement(By.xpath("//div[@id='pageContentBody']/div[8]/div/div[2]/div/div/div[3]/ul/li[5]/span")).click();
        driver.findElement(By.id("remove_from_selected_list_button")).click();
        driver.findElement(By.xpath("//strong/u")).click();
        driver.quit();
    }
}
