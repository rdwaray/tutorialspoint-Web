package automation.hooks;

import automation.utils.driverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

public class hooks {
    @Before
    public void setUp(){
        driverFactory.getDriver();
    }

    @After
    public void clearCookies() {
        WebDriver driver = driverFactory.getDriver();
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

    @AfterClass
    public static void teadDown(){
        driverFactory.quitDriver();
    }
}
