package phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import phonebook.model.Contact;
import phonebook.core.TestBase;
import phonebook.utils.MyDataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddContactTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if (!app.getUserHelper().isLoginLinkPresent()){
            app.getUserHelper().clickOnSignOutButton();
        }
        System.out.println("@BeforeMethod in Test");
        app.getUserHelper().login("portishead@gmail.com", "Password@1");
        //app.getUserHelper().checkSuccessLogin();
    }


    @Test(dataProvider = "addNewContactFromCsv",dataProviderClass = MyDataProvider.class)
    public void addContactFromDataProviderWithCsvFileTest(Contact contact){
        app.getContactHelper().pause(300);
        app.getContactHelper().addNewContactPositiveData(contact);

        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
    }

    @Test(dataProvider = "addNewContact",dataProviderClass = MyDataProvider.class)
    public void addContactFromDataProviderTest(String name,String lastname,String phone,String email,String address,String description){
        app.getContactHelper().addNewContactPositiveData(new Contact()
                .setName(name)
                .setLastName(lastname)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDesc(description));

        Assert.assertTrue(app.getContactHelper().isContactAdded(name));
    }


    @Test
    public void addContactPositiveTest() {
        System.out.println("@Test");
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addNewContactPositiveData(app.getContactHelper().CONTACT_NAME);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
        Assert.assertTrue(app.getContactHelper().isContactAdded(app.getContactHelper().CONTACT_NAME));
    }

    @Test
    public void addContactWODescTest1() {
        Contact contact = new Contact()
                .setName(app.getContactHelper().CONTACT_NAME)
                .setLastName("Test Last Name")
                //.setEmail("portishead@gmail.com") // Убираем
                .setPhone("1234567890")
                .setAddress("Germany, Berlin")
                //.setDesc("Some description") // Убираем
                ;

        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addNewContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
        Assert.assertTrue(app.getContactHelper().isContactAdded(app.getContactHelper().CONTACT_NAME));
    }

    @Test
    public void addContactWODescTest2() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addNewContactPositiveData(new Contact()
                .setName(app.getContactHelper().CONTACT_NAME)
                .setLastName("Test Last Name")
                //.setEmail("portishead@gmail.com") // Убираем
                .setPhone("1234567890")
                .setAddress("Germany, Berlin"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Кол-во контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
        Assert.assertTrue(app.getContactHelper().isContactAdded(app.getContactHelper().CONTACT_NAME));
    }

    @AfterMethod
    public void afterMethodInTest(){
        System.out.println("@AfterMethod in test");
    }
}
