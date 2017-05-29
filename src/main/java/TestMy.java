import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

    @Test /* go to the URL */
    public void test1() {
        driver.navigate().to("http://juliemr.github.io/protractor-demo/");
        Assert.assertEquals(driver.getCurrentUrl(), "http://juliemr.github.io/protractor-demo/");
                        }
    }


