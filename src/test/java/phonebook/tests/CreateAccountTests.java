package phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import phonebook.core.TestBase;

public class CreateAccountTests extends TestBase {
    @Test(invocationCount = 1)
    public void CreateAccountPositiveTest() {
        // click on //a[.='LOGIN'].
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        // enter email to By.name('email')
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(System.currentTimeMillis() + "_qa@test.com"); //! Генерирует рандомный email
        // enter password to By.name('password')
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
        // click on By.name('registration')
        app.driver.findElement(By.name("registration")).click();
        // assert that button //button[.='Sign Out'] is present
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test(invocationCount = 1)
    public void CreateAccountPositiveTest2() {
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().enterEmail(System.currentTimeMillis() + "_qa@test.com");
        app.getUserHelper().enterPassword("Password@1");
        app.getUserHelper().clickOnRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void CreateExistedAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();

        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().enterEmail("portishead2024@gmail.com");
        app.getUserHelper().enterPassword("Password@1");
        app.getUserHelper().clickOnRegistrationButton();

        softAssert.assertEquals(app.getUserHelper().getTextFromAlert(), "User already exist");
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));

        softAssert.assertAll();
    }
}
