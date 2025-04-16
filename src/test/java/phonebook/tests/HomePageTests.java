package phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import phonebook.core.TestBase;

public class HomePageTests extends TestBase{

    @Test
    public void isHomeComponentPresentPositiveTest() {
        Assert.assertTrue(app.getHomePageHelper().isHomeComponentPresent());
    }
}
