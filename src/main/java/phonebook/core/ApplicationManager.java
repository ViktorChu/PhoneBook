package phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import phonebook.fw.ContactHelper;
import phonebook.fw.HomePageHelper;
import phonebook.fw.UserHelper;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    public WebDriverWait wait;
    private final String browser;
    ContactHelper contactHelper;
    HomePageHelper homePageHelper;
    UserHelper userHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    protected void init() {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("chrome_headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }  else {
            //driver = new ChromeDriver();
            throw new IllegalArgumentException("❌ Некорректный браузер: " + browser + ". Доступные варианты: chrome, firefox, edge, safari.");
        }
        //driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // Явное ожидание
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // Неявное ожидание

        contactHelper = new ContactHelper(driver, wait);
        homePageHelper = new HomePageHelper(driver, wait);
        userHelper = new UserHelper(driver, wait);
    }

    public void stop() {
        driver.quit();
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }
}
