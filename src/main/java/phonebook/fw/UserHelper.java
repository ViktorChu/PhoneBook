package phonebook.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import phonebook.core.BaseHelper;
import phonebook.model.User;

public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public boolean isSignOutButtonPresent(){
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public void clickOnRegistrationButton() {
        driver.findElement(By.name("registration")).click();
    }

    public void clickOnLoginLink() {
      //driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        click(By.xpath("//a[.='LOGIN']"));
    }

    public void enterEmail(String email) {
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        type(By.name("email"),email);
        type(By.name("password"),password);
        click(By.name("login"));
    }

    public void login(User user) {
        clickOnLoginLink();
        type(By.name("email"),user.getEmail()); // исправить mail на email - сделал специальную ошибку для проверки работы скриншота при ошибке (запуск теста в 16 строке в LoginTests)
        type(By.name("password"),user.getPassword());
        click(By.name("login"));
    }

    public void checkSuccessLogin() {
        Assert.assertTrue(isSignOutButtonPresent());
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.xpath("//a[.='LOGIN']"));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }
}
