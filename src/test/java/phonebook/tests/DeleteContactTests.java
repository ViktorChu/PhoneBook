package phonebook.tests;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import phonebook.core.TestBase;

public class DeleteContactTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.getUserHelper().isLoginLinkPresent()){
            app.getUserHelper().clickOnSignOutButton();
        }
        
        app.getUserHelper().login("portishead@gmail.com", "Password@1");
        app.getUserHelper().checkSuccessLogin();
        //app.getContactHelper().addNewContact(app.getContactHelper().CONTACT_NAME);
        //app.getContactHelper().addNewContact(app.getContactHelper().CONTACT_NAME);
        //app.getContactHelper().addNewContact(app.getContactHelper().CONTACT_NAME);
    }
    Logger logger = LoggerFactory.getLogger(DeleteContactTests.class);
    @Test
    public void creatOneAndDeleteOnePositiveTest() {
        // Количество контактов ДО удаления.
        int sizeBefore = app.getContactHelper().getContactsCount();
        //System.out.println("Кол-во контактов ДО удаления: " + sizeBefore);
        logger.info("Кол-во контактов ДО удаления: " + sizeBefore);
        // Удалить контакт `CONTACT_NAME`
        app.getContactHelper().deleteOneContact(app.getContactHelper().CONTACT_NAME);
        //pause(1000);
        app.wait.until(driver -> app.getContactHelper().getContactsCount() < sizeBefore);
        // Количество контактов ПОСЛЕ удаления.
        int sizeAfter = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ПОСЛЕ удаления: " + sizeAfter);
        // Сравнить переменные до и после удаления
        Assert.assertEquals(sizeAfter, sizeBefore - 1);
    }

    @Test
    public void deleteAllContactsTest(){
        while(hasContacts()){
            int sizeBefore = app.getContactHelper().getContactsCount();
            System.out.println("Кол-во контактов ДО удаления: " + sizeBefore);
            //click(By.className(CONTACT_LOCATOR));
            app.getContactHelper().click(app.getContactHelper().LOCATOR);
            app.getHomePageHelper().click(app.getContactHelper().BUTTON_REMOVE);
            app.wait.until(driver -> app.getContactHelper().getContactsCount() < sizeBefore);
        }
        if(!hasContacts()){
            System.out.println("Все контакты удалены: " + app.getContactHelper().getContactsCount());
        }
        Assert.assertEquals(app.getContactHelper().getContactsCount(),0,"Не все контакты были удалены: " + app.getContactHelper().getContactsCount());
    }

    private boolean hasContacts() {
        return app.getContactHelper().isElementPresent(By.className(app.getContactHelper().CONTACT_LOCATOR));
    }
}
