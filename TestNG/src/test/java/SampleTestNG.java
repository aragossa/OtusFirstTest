import config.ConfigLoader;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.Assert;

public class SampleTestNG {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTestNG.class);
    private ConfigLoader cfg = ConfigFactory.create(ConfigLoader.class);

    @Test
    public void Log() {
        logger.info("INFO");
        logger.fatal("FATAL");
        logger.error("ERROR");
        logger.debug("DEBUG");
        logger.trace("TRACE");
    }

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
    }

    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница отус");
        driver.manage().window().maximize();
        logger.info("Развернуто на полный экран");
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", driver.getTitle());
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
