package phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import phonebook.core.TestBase;
import phonebook.model.User;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if (!app.getUserHelper().isLoginLinkPresent()){
            app.getUserHelper().clickOnSignOutButton();
        }
    }

    @Test
    public void loginExistedUserPositiveTest(){
        app.getUserHelper().login("portishead@gmail.com", "Password@1");
        app.getUserHelper().checkSuccessLogin();
    }

    @Test
    public void loginExistedUserObject1PositiveTest(){  // запускаем этот тест для проверки работы скриншота на ошибке в UserHelper в строке 49
        app.getUserHelper().login(new User()
                .setEmail("portishead@gmail.com")
                .setPassword("Password@1")
        );
        app.getUserHelper().checkSuccessLogin();
    }

    @Test
    public void loginExistedUserObject2PositiveTest() {
        User user = new User();
        user.setEmail("portishead@gmail.com");
        user.setPassword("Password@1");

        app.getUserHelper().login(user);
        app.getUserHelper().checkSuccessLogin();
    }

    @Test
    public void loginWOEmail1NegativeTest(){
        User user = new User();
        user.setPassword("Password@1");
        app.getUserHelper().login(user);
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }

    @Test
    public void loginWOEmail2NegativeTest(){
        app.getUserHelper().login(new User()
                .setPassword("Password@1")
        );
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }

    @Test
    public void loginWOPasswordNegativeTest(){
        app.getUserHelper().login(new User()
                .setEmail("portishead@gmail.com")
        );
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }
}
