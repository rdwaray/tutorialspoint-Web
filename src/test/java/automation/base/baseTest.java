package automation.base;

import automation.utils.driverFactory;

import static automation.utils.driverFactory.driver;

public class baseTest {
    public void setUp() {
        driver = driverFactory.getDriver();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
