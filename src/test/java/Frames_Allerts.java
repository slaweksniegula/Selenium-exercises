import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Frames_Allerts {
    WebDriver driver;
    Actions actions;
    WebElement element;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void nestedFramesMovementTest() {
        driver.navigate().to("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame(1);
        assertEquals("BOTTOM", driver.findElement(By.tagName("body")).getText());

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        assertEquals("LEFT", driver.findElement(By.tagName("body")).getText());

        driver.switchTo().defaultContent();
        assertTrue(driver.findElement(By.name("frame-top")).getSize().height > 0);
    }

    @Test
    public void allertsTest() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//*[contains(normalize-space(text()), 'Click for JS Alert')]")).click();
        driver.switchTo().alert().dismiss();

        driver.findElement(By.xpath("//*[contains(normalize-space(text()), 'Click for JS Confirm')]")).click();
        driver.switchTo().alert().accept();

        driver.findElement(By.xpath("//*[contains(normalize-space(text()), 'Click for JS Prompt')]")).click();
        Alert inputAlert = driver.switchTo().alert();
        String text = inputAlert.getText();
        inputAlert.sendKeys("This is my alert text");
        inputAlert.accept();
    }

}
