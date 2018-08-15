import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CopyAndAddNewList {
    private static WebDriver driver;
    @BeforeClass
    public static void logIn(){
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
    public void createAndcopy() throws InterruptedException {
        WebElement count2 = driver.findElement(By.xpath("//tbody[@id='listCountsContainer']/tr/td/span/span"));
        String count0 = count2.getText();
        driver.findElement(By.xpath("//label[@id='lblAllPersons']/i")).click();
        driver.findElement(By.xpath("//span[@id='drop-copy']/u")).click();
        driver.findElement(By.xpath("//strong[@id='create_new_and_copy']")).click();
        WebElement input = driver.findElement(By.xpath("//input[@id='subscriber_list_name']"));
        input.sendKeys("CreateNewList");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='lnkListHomepage']")).click();
        driver.findElement(By.xpath("//tbody[@id='contactListsContainer']/tr/td/a")).click();
        WebElement count1 = driver.findElement(By.xpath("//tbody[@id='listCountsContainer']/tr/td/span/span"));
        String count = count1.getText();
        Assert.assertEquals(count0,count);
        System.out.println("Скопировано " + count + " контактов");
        }
        @AfterClass
    public static void clean() {
        driver.findElement(By.xpath("//small[@id='listActionsContainerLnk']")).click();
        driver.findElement(By.xpath("//span[@id='lnkDeleteList']")).click();
        driver.findElement(By.xpath("//strong[@id='btnRemoveListYes']/u")).click();
        driver.quit();
        }
}
