import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    //    @Test
//    public void firstTest() {
//        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
//        WebDriver driver = new ChromeDriver();
//    }
    @Test
    public void loginTest() {
        driver.get("https://1login.wp.pl/zaloguj/dodaj" +
                "?login_challenge=CjoKJDBkYjEyZTNkYjBlNTg3NjNhY2VjYjhjNWQ1NTgwZmQ0ZWNiMhDNlI6JBhoMCgpvbC1wcm9maWxlEiBlRmfrX79B5jBv_auBeyFbrxR9uFb8-CdmKgDNYWCwsQ");
        WebElement element;
        element = driver.findElement(By.id("login"));
    }

    @Test
    public void passwordTest() {
        driver.get("https://1login.wp.pl/zaloguj/dodaj" +
                "?login_challenge=CjoKJDBkYjEyZTNkYjBlNTg3NjNhY2VjYjhjNWQ1NTgwZmQ0ZWNiMhDNlI6JBhoMCgpvbC1wcm9maWxlEiBlRmfrX79B5jBv_auBeyFbrxR9uFb8-CdmKgDNYWCwsQ");
        WebElement element;
        element = driver.findElement(By.id("password"));
    }

    @Test
    public void orderTest() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://saucedemo.com/");
        //css selectors
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        //css/Xpath selectors
        driver.findElement(By.xpath("//*[@id='item_4_title_link']/child::div")).click();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a")).click();
        driver.findElement(By.xpath("//*[@class='cart_footer']/button[not(*)]")).click();

        driver.findElement(By.name("firstName")).sendKeys("first name");
        driver.findElement(By.id("last-name")).sendKeys("last name");
        driver.findElement(By.id("postal-code")).sendKeys("zip");
        driver.findElement(By.xpath("//*[@class='checkout_buttons']/*[2]")).click();

        driver.findElement(By.xpath("//*[@class='cart_footer']/*[2]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());
    }
}
