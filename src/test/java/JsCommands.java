import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.jar.asm.Handle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class JsCommands {

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
    public void tabsSwitchingTest() {
        driver.navigate().to("https://the-internet.herokuapp.com/windows");

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open('https://the-internet.herokuapp.com/windows/new');");

        String originalWindow = driver.getWindowHandle();
        Set handles = driver.getWindowHandles();
        handles.remove(originalWindow);

        String secondWindow = String.valueOf(handles.iterator().next());
        driver.switchTo().window(secondWindow);

        assertEquals("New Window", driver.getTitle());

    }
}
