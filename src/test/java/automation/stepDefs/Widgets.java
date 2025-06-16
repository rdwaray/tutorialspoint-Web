package automation.stepDefs;

import automation.utils.Utilities;
import automation.utils.driverFactory;
import com.github.javafaker.Faker;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Widgets {

    private WebDriver driver = driverFactory.getDriver();
    private WebDriverWait wait = driverFactory.getWait();
    private Faker faker = driverFactory.getFaker();
    @Given("I am on Widgets Test page")
    public void iAmOnWidgetsTestPage() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/accordion.php");
    }

    @Given("I am on Accordian Test Page")
    public void iAmOnAccordianTestPage() {
        By widgetsDrop = By.xpath("//button[normalize-space()='Widgets']");
        Utilities.clickMultipleTimes(driver,widgetsDrop,2, 1000);

        By textBox = By.xpath("//a[normalize-space()='Accordion']");
        Utilities.safeClick(driver,textBox);
    }

    @Then("I click on first Accordian")
    public void iClickOnFirstAccordian() {
        By accordianOne = By.id("headingTwentyOne");
        Utilities.safeClick(driver, accordianOne);

        By accordianOneText = By.xpath("//div[@id='collapseTwentyOne']//p[@class='text-justify']");
        Utilities.getElementTextSafe(driver, accordianOneText, "Message Not Found");
    }

    @And("I click on second Accordian")
    public void iClickOnSecondAccordian() {

        By accordianTwo = By.id("headingTwentyTwo");
        Utilities.safeClick(driver, accordianTwo);

        By accordianTwoText = By.xpath("//p[contains(text(),'It is a long established fact that a reader will b')]");
        Utilities.getElementTextSafe(driver, accordianTwoText, "Message Not Found");


    }

    @When("I click on third Accordian")
    public void iClickOnThirdAccordian() {
        By accordianThree = By.id("headingTwentyThree");
        Utilities.safeClick(driver, accordianThree);

        By accordianThreeText = By.xpath("//p[contains(text(),'There are many variations of passages of Lorem Ips')]");
        Utilities.getElementTextSafe(driver, accordianThreeText, "Message Not Found");


    }

    @Then("I close all Accordian")
    public void iCloseAllAccordian() {
        List<By> multipleAccordia = Arrays.asList(
                By.id("headingTwentyOne"),
                By.id("headingTwentyTwo"),
                By.id("headingTwentyThree")
        );
        Utilities.clickMultipleElements(driver,multipleAccordia);
    }

    @Given("I am on Auto Complete Test Page")
    public void iAmOnAutoCompleteTestPage() {
        By widgetsDrop = By.xpath("//button[normalize-space()='Widgets']");
        Utilities.clickMultipleTimes(driver,widgetsDrop,2, 1000);

        By autoCompleteBox = By.xpath("//a[normalize-space()='Auto Complete']");
        Utilities.safeClick(driver,autoCompleteBox);
    }

    @Then("I fill form with {string}")
    public void iFillFormWith(String tags1) {
        By autoCompleteBox = By.id("tags");
        Utilities.sendKeys(driver,autoCompleteBox,tags1);

    }

    @And("I choose {string}")
    public void iChoose(String chose) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));

        driver.findElement(By.xpath("//ul[@id='ui-id-1']//div[normalize-space()='" + chose + "']")).click();
    }

    @Given("I am on Date Picker Test page")
    public void iAmOnDatePickerTestPage() {
        By widgetsDrop = By.xpath("//button[normalize-space()='Widgets']");
        Utilities.clickMultipleTimes(driver,widgetsDrop,2, 1000);

        By autoCompleteBox = By.xpath("//a[normalize-space()='Date Picker']");
        Utilities.safeClick(driver,autoCompleteBox);
    }

    @Then("I input Date")
    public void iInputDate() {

        WebElement dateInput = driver.findElement(By.id("datetimepicker1"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String targetDateTime = "2025-06-21 10:30";

        js.executeScript("arguments[0]._flatpickr.setDate(arguments[1]);", dateInput, targetDateTime);

    }

    @And("I input date and time")
    public void iInputDateAndTime() {
        WebElement dateInput = driver.findElement(By.id("datetimepicker2"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String targetDateTime = "2025-06-11 10:30";

        js.executeScript("arguments[0]._flatpickr.setDate(arguments[1]);", dateInput, targetDateTime);
    }

    @Given("I am on Slider Test Page")
    public void iAmOnSliderTestPage() {
        By sliderTest = By.xpath("//a[normalize-space()='Slider']");
        Utilities.safeClick(driver, sliderTest);
    }

    @Then("I am changing the value of slider")
    public void iAmChangingTheValueOfSlider() {
        WebElement slider = driver.findElement(By.id("ageInputId"));
        Actions move = new Actions(driver);
        Action action = (Action) move.dragAndDropBy(slider,70,0)
                .build();
        action.perform();

        By disabledSlider = By.id("disabledRange");
        Utilities.isElementInteractable(driver,disabledSlider,1000);
    }

    @Given("I am on Progress Bar Test page")
    public void iAmOnProgressBarTestPage() {
        By sliderTest = By.xpath("//a[normalize-space()='Progress Bar']");
        Utilities.safeClick(driver, sliderTest);
    }

    @Then("I click on start progress button")
    public void iClickOnStartProgressButton() {
        By startButton = By.id("startProgressTimer");
        Utilities.safeClick(driver,startButton);
    }

    @And("I extract value on progres bar")
    public void iExtractValueOnProgresBar() {
        Utilities.loading(6000);
        WebElement progressBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));
        String progressBarValue = progressBar.getAttribute("Style");
        System.out.println(progressBarValue);
    }

    @Given("I am on Tab Test Page")
    public void iAmOnTabTestPage() {
        By tabTest = By.xpath("//a[normalize-space()='Tabs']");
        Utilities.safeClick(driver, tabTest);
    }

    @Then("I click on Home tab")
    public void iClickOnHomeTab() {
        By homeTab = By.id("nav-home-tab");
        Utilities.safeClick(driver,homeTab);
        By homeText = By.id("nav-home");
        Utilities.getElementTextSafe(driver,homeText, "Message Not Found");
    }

    @And("I click on Profile tab")
    public void iClickOnProfileTab() {
        By profileTab = By.id("nav-profile-tab");
        Utilities.safeClick(driver,profileTab);
        By homeText = By.id("nav-profile");
        Utilities.getElementTextSafe(driver,homeText, "Message Not Found");
    }

    @And("I click on Contact tab")
    public void iClickOnContactTab() {
        By contactTab = By.id("nav-contact-tab");
        Utilities.safeClick(driver,contactTab);
        By homeText = By.id("nav-contact");
        Utilities.getElementTextSafe(driver,homeText, "Message Not Found");
    }

    @Given("I am on Tools Tips Test page")
    public void iAmOnToolsTipsTestPage() {
        By toolTest = By.xpath("//a[normalize-space()='Tool Tips']");
        Utilities.safeClick(driver, toolTest);
    }

    @Then("I hover mouse to element one")
    public void iHoverMouseToElementOne() {
        WebElement hoverOne = driver.findElement(By.xpath("//button[@title='Tooltip on top']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverOne).perform();
        Utilities.loading(500);
    }

    @And("I hover mouse to element two")
    public void iHoverMouseToElementTwo() {
        WebElement hoverTwo = driver.findElement(By.xpath("//button[@title='Tooltip on right']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverTwo).perform();
        Utilities.loading(500);

    }

    @And("I hover mouse to element three")
    public void iHoverMouseToElementThree() {
        WebElement hoverThree = driver.findElement(By.xpath("//button[@title='Tooltip on bottom']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverThree).perform();
        Utilities.loading(500);

    }

    @And("I hover mouse to element four")
    public void iHoverMouseToElementFour() {
        WebElement hoverFour = driver.findElement(By.xpath("//button[@title='Tooltip on left']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverFour).perform();
        Utilities.loading(500);

    }

    @Given("I am on Menu Test page")
    public void iAmOnMenuTestPage() {
        By menuTest = By.xpath("//a[normalize-space()='Menu']");
        Utilities.safeClick(driver, menuTest);
    }

    @Then("I am hovering to Dropdown menu")
    public void iAmHoveringToDropdownMenu() {
        By dropDown = By.xpath("//a[@role='button']");
        Utilities.safeClick(driver, dropDown);

        WebElement menuA = driver.findElement(By.xpath("//a[normalize-space()='Action']"));
        WebElement menuB = driver.findElement(By.xpath("//a[normalize-space()='Another action']"));
        WebElement menuC = driver.findElement(By.xpath("//a[normalize-space()='Something else here']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(menuA).perform();
        Utilities.loading(500);
        actions.moveToElement(menuB).perform();
        Utilities.loading(500);
        actions.moveToElement(menuC).perform();
        Utilities.loading(500);


    }

    @Given("I am on Select Menu Test page")
    public void iAmOnSelectMenuTestPage() {
        By menuTest = By.xpath("//a[normalize-space()='Select Menu']");
        Utilities.safeClick(driver, menuTest);

    }

    @Then("I interact with Select One menu")
    public void iInteractWithSelectOneMenu() {
        WebElement dropdown = driver.findElement(By.xpath("//*[@id='inputGroupSelect03']"));
        Select select = new Select(dropdown);
        select.selectByValue("1");

    }

    @Given("I am on Scroll Top test page")
    public void iAmOnScrollTopTestPage() {
        By menuTest = By.xpath("//a[normalize-space()='Scoll Top']");
        Utilities.safeClick(driver, menuTest);
    }

    @And("I scroll header")
    public void iScrollHeader() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        By top = By.id("btn-back-to-top");
        Utilities.safeClick(driver,top);
    }

    @Then("I am on Scroll Down test page")
    public void iAmOnScrollDownTestPage() {
        By menuTest = By.xpath("//a[normalize-space()='Scoll Down']");
        Utilities.safeClick(driver, menuTest);
    }

    @And("I scroll to footer")
    public void iScrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        Utilities.loading(1400);
        By down = By.xpath("//a[@class='scrollDown btn btn-primary btn-floating btn-lg']//*[name()='svg']");
        Utilities.safeClick(driver,down);
    }
}

