package phonebook.core;


import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseHelper {
    public WebDriver driver;
    public WebDriverWait wait;
    Logger logger = LoggerFactory.getLogger(BaseHelper.class);

    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public String getTextFromAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Текст внутри алерта: `" + alertText + "`");
        return alertText;
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
            logger.info("Пауза сработала: " + millis + " секунд");
        } catch (InterruptedException e) {
            logger.warn(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String takeScreenshot(){ // сохранение скриншотов, но если есть алерты, то он не работает (нужно писать отдельный блок кода)

        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        File screen = new File("screenshots/screen_" + System.currentTimeMillis() + ".png");

        try {
            Files.copy(tmp,screen); // копируем файл tmp в файл screen
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screen.getAbsolutePath();
    }

}
