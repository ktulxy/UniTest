import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CreateContact {

    private static WebDriver driver;

    @BeforeClass
    public static void goToContact() {
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
        driver.findElement(By.cssSelector("small.plink.gray.bld.lst-add.btnListAddSubscriber")).click();
    }

    @Test
    public void createCont() throws InterruptedException {
       WebElement user = driver.findElement(By.id("subscriber_contacts_address_contact"));
        Thread.sleep(1000);
       user.sendKeys("joinme@bigmir.net");
       WebElement phone = driver.findElement(By.id("subscriber_contacts_phone_contact"));
       phone.sendKeys("+380987654321");
       WebElement name = driver.findElement(By.id("subscriber_fields_6515377_value"));
       name.sendKeys("Oleg");
       driver.findElement(By.id("saveSubscriber")).click();
        Thread.sleep(2000);
       driver.findElement(By.xpath("//tbody[@id='contactListsContainer']/tr/td/a")).click();
       WebElement userGet =  driver.findElement(By.xpath("//div[2]/div[3]/div/table/tbody/tr/td/span"));
       String userAssert = userGet.getText();
       Assert.assertEquals("joinme@bigmir.net", userAssert);

    }

     @AfterClass
    public static void cleanAfterMe() throws InterruptedException {
        driver.findElement(By.cssSelector("span.plink.edtLink")).click();
       Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@id='lnkDelete']/u")).click();
        driver.quit();
    }


    /*public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
    }*/

    /*public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)getDriver()).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }*/
}

