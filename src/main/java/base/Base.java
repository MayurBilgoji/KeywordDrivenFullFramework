package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base
{
    public WebDriver driver;
    public Properties prop;
    public static final Logger log = LogManager.getLogger(Base.class.getName());

    public WebDriver init_driver(String browserName)
    {
        if(browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Asus\\IdeaProjects\\AutomationWithKeywordDriven\\drivers\\chromedriver.exe");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(35));
            //System.out.println("Launching browser");
            log.info("Launching browser");

        }
        else if(browserName.equalsIgnoreCase("chrome_Options"))
        {
            System.setProperty("webdriver.chrome.driver", "/Users/mayur.bilgoji/IdeaProjects/JPGJio/drivers/chromedriver");
            ChromeOptions options=new ChromeOptions();
            options.addArguments("user-data-dir=/Users/mayur.bilgoji/Library/Application Support/Google/Chrome");
            driver=new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            log.info("Launching browser with options");
        }
        return driver;
    }

    public Properties init_properties()
    {
        prop=new Properties();
        try {
            FileInputStream fis=new FileInputStream("C:\\Users\\Asus\\IdeaProjects\\AutomationWithKeywordDriven\\src\\main\\java\\properties\\config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop;

    }



}
