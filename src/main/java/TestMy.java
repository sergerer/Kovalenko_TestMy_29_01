import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Serge on 23.05.2017.
 */
public class TestMy {
    private WebDriver driver;

    @BeforeTest
    public void setup() {

        final File file = new File(PropertyLoader.loadProperty("path.webDriver"));
        System.setProperty(PropertyLoader.loadProperty("webDriver"), file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /* go to the URL */
    @Test
    public void test1() {
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
        Assert.assertEquals(driver.getCurrentUrl(), "http://juliemr.github.io/protractor-demo/");
    }

    /* input digits into fields*/
    @Test(dependsOnMethods = "test1")
    public void test2() {
        WebElement FirstField = driver.findElement(By.xpath("//input[@ng-model='first']"));
        FirstField.sendKeys("1");
        WebElement SecondField = driver.findElement(By.xpath("//input[@ng-model='second']"));
        SecondField.sendKeys("1");
        Assert.assertEquals(FirstField.getAttribute("value"), "1");
        Assert.assertEquals(SecondField.getAttribute("value"), "1");
    }
    /* select operation with digits */
    @Test(dependsOnMethods = "test2", alwaysRun = true)
    public void test3() {
        Select action = new Select(driver.findElement(By.xpath("//select[@ng-model='operator']")));
        action.selectByVisibleText("+");
        String proverka = driver.findElement(By.xpath("//option[1]")).getText();
        Assert.assertEquals(proverka, "+");

    }

    /* click calculation --> check the result */
    @Test(dependsOnMethods = "test3", alwaysRun = true)
    public void test4() throws InterruptedException {

        WebElement goButton = driver.findElement(By.xpath(".//*[@id='gobutton']"));
        goButton.click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.withTimeout(10, TimeUnit.SECONDS);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//h2"), String.valueOf('2')));
    }

    /*close browser/off selenium*/
    @AfterTest(alwaysRun = true)

    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}

   /* click calculation --> check the result */
/**   @Test(dependsOnMethods = "test3", alwaysRun = true)
    public void test4() throws InterruptedException {

        WebElement goButton = driver.findElement(By.xpath(".//*[@id='gobutton']"));
        goButton.click();
        WebElement result = driver.findElement(By.xpath("//td[3]"));
        Assert.assertEquals(result.getText(), "2");
        /* Thread.sleep(8000); - works as expected without additional delay*/