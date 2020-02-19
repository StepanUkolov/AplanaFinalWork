package ru.aplana.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.aplana.util.Product;
import ru.aplana.util.TestProperties;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.getInstance().getProperties();
    public static ArrayList<Product> productsList = new ArrayList<>();

    public static WebDriver getDriver() {
        return driver;
    }


    @Before
    public static void setUp() throws MalformedURLException {

        switch (properties.getProperty("browser")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
        }
        baseUrl = properties.getProperty("app.url");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(baseUrl);

    }


    @After
    public static void tearDown() {
        driver.quit();
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public static ArrayList<Product> attachProductsList(){
        return productsList;
    }
    @Attachment
    public static Product attachMaxProductPrice(){
        return productsList.stream().max(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.parseInt(o1.getProductPrice()) - Integer.parseInt(o2.getProductPrice());
            }
        }).get();
    }

    public static void addToProductsList(Product product) {
        productsList.add(product);
    }
}
