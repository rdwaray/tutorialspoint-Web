package automation.stepDefs;

import automation.utils.Utilities;
import automation.utils.driverFactory;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class alertFrameWindow {

    private WebDriver driver = driverFactory.getDriver();
    private WebDriverWait wait = driverFactory.getWait();
    private Faker faker = driverFactory.getFaker();


    @Given("I am on the Alert, Frame, and Windows test page")
    public void iAmOnTheAlertFrameAndWindowsTestPage() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");
    }

    @Given("I am on browser windows test page")
    public void iAmOnBrowserWindowsTestPage() {

        By elementsDrop = By.xpath("//button[normalize-space()='Alerts, Frames & Windows']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By textBox = By.xpath("//a[normalize-space()='Browser Windows']");
        Utilities.safeClick(driver,textBox);
    }

    @Then("I click on New Tab button")
    public void iClickOnNewTabButton() {
        By newTabButton = By.xpath("//button[@title='New Tab']");
        Utilities.safeClick(driver,newTabButton);
    }

    @And("I should be in different windows and back to maintab")
    public void iShouldBeInDifferentWindowsAndBackToMaintab() {
        System.out.println(driver.getTitle());

    }

    @When("I click on New Window")
    public void iClickOnNewWindow() {
        Utilities.switchToWindowByIndex(driver,0);
        By newWindowButton = By.xpath("//button[normalize-space()='New Window']");
        Utilities.safeClick(driver,newWindowButton);

    }

    @Then("I should be in new window browser")
    public void iShouldBeInNewWindowBrowser() {
        System.out.println(driver.getTitle());
    }

    @And("I click on New Windows Message")
    public void iClickOnNewWindowsMessage() {
        Utilities.switchToWindowByIndex(driver,0);
        By newWindowMessageButton = By.xpath("//button[normalize-space()='New Window Message']");
        Utilities.safeClick(driver,newWindowMessageButton);
    }

    @Then("I should be in new windows message")
    public void iShouldBeInNewWindowsMessage() {
        System.out.println(driver.getTitle());
        Utilities.switchToWindowByIndex(driver,2);
        driver.close();
        Utilities.switchToWindowByIndex(driver,1);
        driver.close();
        Utilities.switchToDefaultTab(driver);
    }

    @Given("I am on Alert test page")
    public void iAmOnAlertTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Alerts, Frames & Windows']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By textBox = By.xpath("//a[normalize-space()='Alerts']");
        Utilities.safeClick(driver,textBox);
    }

    @Then("I click on first alert and click OK")
    public void iClickOnFirstAlertAndClickOK() {
        By alert1 = By.xpath("//button[normalize-space()='Alert']");
        Utilities.safeClick(driver,alert1);
        Utilities.getAlertText(driver);
        Utilities.acceptAlert(driver);
    }

    @And("I click on second alert, wait for five seconds then click OK")
    public void iClickOnSecondAlertWaitForFiveSecondsThenClickOK() {
        By alert2 = By.xpath("//button[@onclick='myMessage()']");
        Utilities.safeClick(driver,alert2);
        Utilities.loading(5500);
        Utilities.getAlertText(driver);
        Utilities.acceptAlert(driver);

    }

    @And("I click on confirm alert")
    public void iClickOnConfirmAlert() {
        By alert3 = By.xpath("//button[@onclick='myDesk()']");
        Utilities.safeClick(driver,alert3);
        Utilities.getAlertText(driver);
        Utilities.acceptAlert(driver);

        Utilities.safeClick(driver,alert3);
        Utilities.dismissAlert(driver);

    }

    @And("I click prompt box alert and fill {string}")
    public void iClickPromptBoxAlertAndFill(String name) {
        By alert4 = By.xpath("//button[@onclick='myPromp()']");
        Utilities.safeClick(driver,alert4);
        Utilities.sendKeysToAlert(driver,name);
        Utilities.acceptAlert(driver);
    }

    @Given("I am on Frames test page")
    public void iAmOnFramesTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Alerts, Frames & Windows']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By textBox = By.xpath("//a[normalize-space()='Frames']");
        Utilities.safeClick(driver,textBox);
    }

    @Then("I switch to iframe one")
    public void iSwitchToIframeOne() {
        WebElement iframe = driver.findElement(By.xpath("/html/body/main/div/div/div[2]/iframe[1]"));
        driver.switchTo().frame(iframe);

        String frame1Text = driver.findElement(By.tagName("body")).getText();
        System.out.println(frame1Text);
    }

    @And("I switch to iframe two")
    public void iSwitchToIframeTwo() {
        driver.switchTo().defaultContent();
        WebElement iframe2 = driver.findElement(By.xpath("/html/body/main/div/div/div[2]/iframe[2]"));
        driver.switchTo().frame(iframe2);

        String frame2Text = driver.findElement(By.tagName("body")).getText();
        System.out.println(frame2Text);
    }


    @Given("I am on Modal Dialogs test page")
    public void iAmOnModalDialogsTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Alerts, Frames & Windows']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By textBox = By.xpath("//a[normalize-space()='Modal Dialogs']");
        Utilities.safeClick(driver,textBox);
    }

    @Then("I click on Small Modal Button")
    public void iClickOnSmallModalButton() {
        By smallModalButton = By.xpath("//button[normalize-space()='Small Modal']");
        Utilities.safeClick(driver,smallModalButton);
    }

    @And("I extract Small Modal text")
    public void iExtractSmallModalText() {
        By smallModalMessage = By.xpath("//*[@id=\"exampleModalSm\"]/div/div/div[2]");
        Utilities.getElementTextSafe(driver,smallModalMessage,"Message Not Found");
    }

    @And("Close Small Modal prompt")
    public void closeSmallModalPrompt() {
        By closeSmall = By.xpath("//*[@id=\"exampleModalSm\"]/div/div/div[2]");
        Utilities.safeClick(driver,closeSmall);
    }

    @Then("I click on Large Modal Button")
    public void iClickOnLargeModalButton() {
        By largeModalButton = By.xpath("//button[normalize-space()='Large Modal']");
        Utilities.safeClick(driver,largeModalButton);
    }

    @And("I extract Large Modal text")
    public void iExtractLargeModalText() {
        By largeModalMessage = By.xpath("//*[@id=\"exampleModalLg\"]/div/div/div[2]");
        Utilities.getElementTextSafe(driver,largeModalMessage,"Message Not Found");

    }


    @And("Close Large Modal prompt")
    public void closeLargeModalPrompt() {
        By closeLarge = By.xpath("//*[@id=\"exampleModalLg\"]/div/div/div[2]");
        Utilities.safeClick(driver,closeLarge);

    }
}
