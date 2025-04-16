package phonebook.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import phonebook.core.BaseHelper;

public class HomePageHelper extends BaseHelper {
    public HomePageHelper(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    public boolean isHomeComponentPresent() {
       return isElementPresent(By.cssSelector("body>div>div:nth-child(2)>div>div>h1"));
   }
}
