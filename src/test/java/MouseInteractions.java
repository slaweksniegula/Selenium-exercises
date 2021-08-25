import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*;

public class MouseInteractions {
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
    public void dropdownTest() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        element = driver.findElement(By.id("dropdown"));
        element.click();
        WebElement option1 = driver.findElement(By.cssSelector("option[value='1']"));
        WebElement option2 = driver.findElement(By.cssSelector("option[value='2']"));
        option1.click();
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());
    }
    @Test
    public void hoverTest() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        element = driver.findElement(By.xpath("//*[@href='/users/1']/../../img"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'name: user1')]"));
        assertTrue("user1 should appear when we hover over first image", text.isDisplayed());
    }
    @Test
    public void contextMenuTest(){
        driver.get("https://the-internet.herokuapp.com/context_menu");
        element = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        driver.switchTo().alert().accept();
    }
    @Test
    public void keyPress(){
        driver.get("https://the-internet.herokuapp.com/key_presses");
        element = driver.findElement(By.id("target"));
        element.click();
        Actions actions= new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT).pause(100).perform();
        element = driver.findElement(By.id("result"));
        assertEquals("right arrow clicked","You entered: RIGHT", element.getText());
    }
    @Test
    public void getCSSValue(){
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation");
        element = driver.findElement(By.linkText("Clickable Icon"));
        String link = element.getAttribute("href");
        assertEquals("https://ultimateqa.com/link-success/", link);
    }
}
