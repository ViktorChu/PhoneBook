package phonebook.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void startTest(){
        app.init();
    }

    @BeforeMethod
    public void setUp(Method method, Object[] p) {
        logger.info("logger info: " + method.getName() + "with data: " + Arrays.asList(p));
        logger.warn("logger warn");
        logger.error("logger error");
        logger.debug("logger debug");
    }

    @AfterSuite
    public void stopTest(){
        app.stop();
    }

    @AfterMethod(enabled = true)
    public void tearDown(ITestResult result) {
        if (result.isSuccess()) {
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: " + result.getMethod().getMethodName() + "Screenshot: " + app.getUserHelper().takeScreenshot());
        }
        logger.info("logger info=========================================================");

    }
}
