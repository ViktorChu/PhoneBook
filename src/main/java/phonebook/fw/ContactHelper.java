package phonebook.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import phonebook.core.BaseHelper;
import phonebook.model.Contact;

import java.util.List;

public class ContactHelper extends BaseHelper {
    public final String CONTACT_NAME = "Contact Name";
    public final String CONTACT_LOCATOR = "contact-item_card__2SOIM";
    public final By LOCATOR = By.className(CONTACT_LOCATOR);
    public final By BUTTON_REMOVE = By.xpath("//button[.='Remove']");

    public ContactHelper(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }


    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.className("contact-item_card__2SOIM"));
        for(WebElement element : contacts){
            if(element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    public void addNewContactPositiveData(String name) {
        click(By.xpath("//a[.='ADD']"));
        type(By.xpath("//input[@placeholder='Name']"), name);
        type(By.xpath("//input[@placeholder='Last Name']"), "Contact Lastname");
        type(By.xpath("//input[@placeholder='Phone']"), "1234567890");
        type(By.xpath("//input[@placeholder='email']"), "portishead@gmail.com");
        type(By.xpath("//input[@placeholder='Address']"), "Germany, Berlin");
        type(By.xpath("//input[@placeholder='description']"), "Some description");
        click(By.xpath("//button[.='Save']"));
        pause(100); // Может пригодиться (задержка исполнения)
    }

    public void addNewContactPositiveData(Contact contact) {
        click(By.xpath("//a[.='ADD']"));
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDesc());
        click(By.xpath("//button[.='Save']"));
        pause(100); // Может пригодиться
    }

    public int getContactsCount() {
        return driver.findElements(By.className(CONTACT_LOCATOR)).size();
    }

    public void addNewContact(String name){
        int contactsBefore = getContactsCount();
        System.out.println("Кол-во контактов ДО создания: " + contactsBefore);
        addNewContactPositiveData(name);
        int contactsAfter = getContactsCount();
        System.out.println("Кол-во контактов ПОСЛЕ создания: " + contactsAfter);
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
        Assert.assertTrue(isContactAdded(CONTACT_NAME));
    }

    public void deleteOneContact(String contactToDelete) {
        click(By.xpath("//h2[.='" + contactToDelete + " ']"));
        click(By.xpath("//button[.='Remove']"));
    }
}
