package automation.stepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import automation.utils.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class Elements {
    private WebDriver driver = driverFactory.getDriver();
    private WebDriverWait wait = driverFactory.getWait();
    private Faker faker = driverFactory.getFaker();
    @Given("I am on the Elements Test page")
    public void iAmOnTheElementsTestPage() {
        String mainWindowHandle = driver.getWindowHandle();
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");
    }

    @Given("I am on the Text Box test page")
    public void iAmOnTheTextBoxTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By textBox = By.xpath("//a[normalize-space()='Text Box']");
        Utilities.safeClick(driver,textBox);
    }

    @Then("I fill Full Name")
    public void iFillFullName() {
        By fullNameForm = By.id("fullname");
        String fullName = Utilities.generateFullName();
        Utilities.sendKeys(driver,fullNameForm,fullName);
    }

    @And("I fill Email")
    public void iFillEmail() {
        By emailForm = By.id("email");
        String email = Utilities.generateEmail();
        Utilities.sendKeys(driver,emailForm, email);
    }

    @And("I fill Current Address")
    public void iFillCurrentAddress() {
        By addressForm = By.id("address");
        String address = Utilities.generateAddress();
        Utilities.sendKeys(driver,addressForm, address);
    }

    @When("I fill Password")
    public void iFillPassword() {
        By passwordForm = By.id("password");
        String password  =Utilities.generatePassword();
        Utilities.sendKeys(driver,passwordForm,password);
    }

    @Then("I click on Submit button")
    public void iClickOnSubmitButton() {
        By submitButton = By.xpath("//input[@value='Submit']");
        Utilities.safeClick(driver, submitButton);
    }

    @Given("I am on the Check Box test page")
    public void iAmOnTheCheckBoxTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By checkBox = By.xpath("//a[normalize-space()='Check Box']");
        Utilities.safeClick(driver,checkBox);
    }

    @Then("I click on Main Level One")
    public void iClickOnMainLevelOne() {
        By mainLevelOne = By.xpath("//*[@id=\"bs_1\"]/span[1]");
        Utilities.safeClick(driver,mainLevelOne);
    }

    @When("I click on Sub Level One and Two")
    public void iClickOnSubLevelOneAndTwo() {
        By subLevelOne = By.xpath("//*[@id=\"bf_1\"]/span[1]");
        Utilities.safeClick(driver,subLevelOne);

        By subLevelTwo = By.xpath("//*[@id=\"bf_2\"]/span[1]");
        Utilities.safeClick(driver,subLevelTwo);
    }

    @Then("I click on all Main Level One check box")
    public void iClickOnAllMainLevelOneCheckBox() {
        List<By> multipleButtonOne = Arrays.asList(
                By.id("c_io_1"),
                By.id("c_io_2"),
                By.id("c_io_3"),
                By.id("c_io_4"),
                By.id("c_io_5"),
                By.id("c_io_6"),
                By.id("c_io_7"),
                By.id("c_io_8")
        );
        Utilities.clickMultipleElements(driver,multipleButtonOne);
    }

    @And("I click on Main Level Two")
    public void iClickOnMainLevelTwo() {
        By mainLevelTwu = By.xpath("//*[@id=\"bs_2\"]/span[1]");
        Utilities.safeClick(driver,mainLevelTwu);
    }

    @When("I click on Sub Level Three and Four")
    public void iClickOnSubLevelThreeAndFour() {
        By subLevelThree = By.xpath("//*[@id=\"bf_3\"]/span[1]");
        Utilities.safeClick(driver,subLevelThree);

        By subLevelFour = By.xpath("//*[@id=\"bf_4\"]/span[1]");
        Utilities.safeClick(driver,subLevelFour);

    }

    @Then("I click on all Main Level Two check box")
    public void iClickOnAllMainLevelTwoCheckBox() {
        List<By> multipleButtonTwo = Arrays.asList(
                By.id("c_io_9"),
                By.id("c_io_10"),
                By.id("c_io_11"),
                By.id("c_io_12"),
                By.id("c_io_13"),
                By.id("c_io_14"),
                By.id("c_io_15"),
                By.id("c_io_16")
        );
        Utilities.clickMultipleElements(driver,multipleButtonTwo);
    }

    @Given("I am on the Radio Button test page")
    public void iAmOnTheRadioButtonTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By radioButton = By.xpath("//a[normalize-space()='Radio Button']");
        Utilities.safeClick(driver,radioButton);
    }

    @Then("I click on Yes radio button")
    public void iClickOnYesRadioButton() {
        By yesRadio = By.xpath("//input[@value='igottwo']");
        Utilities.safeClick(driver,yesRadio);
        By yesMessage = By.id("check");
        Utilities.getElementTextSafe(driver,yesMessage,"Message Not Found");
    }

    @And("I click on Impressive radio button")
    public void iClickOnImpressiveRadioButton() {
        By impressiveRadio = By.xpath("//input[@value='igotthree']");
        Utilities.safeClick(driver,impressiveRadio);
        By yesMessage = By.id("check1");
        Utilities.getElementTextSafe(driver,yesMessage,"Message Not Found");
    }

    @And("I check Disabled radio button")
    public void iCheckDisabledRadioButton() {
        By noRadio = By.xpath("/html/body/main/div/div/div[2]/form/div[5]/input");
        Utilities.isElementInteractable(driver,noRadio,600);
    }

    @Given("I am on Buttons test")
    public void iAmOnButtonsTest() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By Buttons = By.xpath("//a[normalize-space()='Buttons']");
        Utilities.safeClick(driver,Buttons);
    }

    @When("I click on Double Click Me button")
    public void iClickOnDoubleClickMeButton() {
        By doubleClick = By.xpath("//button[normalize-space()='Double Click Me']");
        Utilities.doubleClick(driver,doubleClick);
    }

    @Then("I should see input double click message")
    public void iShouldSeeInputDoubleClickMessage() {
        By doubleMessage = By.id("doublec");
        Utilities.getElementTextSafe(driver,doubleMessage, "Message Not Found");
    }

    @And("I click on Right Click Me button")
    public void iClickOnRightClickMeButton() {
        By rightClick = By.xpath("//button[normalize-space()='Right Click Me']");
        Utilities.rightClickWithJS(driver,rightClick,100);
    }


    @And("I click on Click Me button")
    public void iClickOnClickMeButton() {
        By leftClick = By.xpath("//button[normalize-space()='Click Me']");
        Utilities.safeClick(driver,leftClick);
    }

    @Then("I should see input click message")
    public void iShouldSeeInputClickMessage() {
        By leftMessage = By.id("welcomeDiv");
        Utilities.getElementTextSafe(driver,leftMessage, "Message Not Found");
    }

    @Given("I am on Links test")
    public void iAmOnLinksTest() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By Buttons = By.xpath("//a[normalize-space()='Links']");
        Utilities.safeClick(driver,Buttons);
    }

    @When("I click on Home link")
    public void iClickOnHomeLink() {
        By homeLink = By.xpath("//a[normalize-space()='Home']");
        Utilities.safeClick(driver,homeLink);
        String mainWindowHandle = driver.getWindowHandle();

    }

    @And("I go back to maintab")
    public void iGoBackToMaintab() {
        Utilities.switchToDefaultTab(driver);
    }

    @Then("I click on Dynamic Home  link")
    public void iClickOnDynamicHomeLink() {
        By dyanmicHome = By.xpath("/html/body/main/div/div/div[2]/p[2]/a");
        Utilities.safeClick(driver, dyanmicHome);
    }

    @When("I click on Created link")
    public void iClickOnCreatedLink() {
        By createdLink = By.id("created");
        Utilities.findElement(driver, createdLink, "center");
        Utilities.safeClick(driver, createdLink);

    }

    @Then("I click on No Content")
    public void iClickOnNoContent() {
        By noContentLink = By.id("no-content");
        Utilities.findElement(driver, noContentLink, "center");

    }

    @And("I click on Moved link")
    public void iClickOnMovedLink() {
        By movedLink = By.id("moved");
        Utilities.findElement(driver, movedLink, "center");
        Utilities.safeClick(driver, movedLink);

    }

    @And("I click on Bad Request link")
    public void iClickOnBadRequestLink() {
        By badRequestLink = By.id("bad-request");
        Utilities.findElement(driver, badRequestLink, "center");
        Utilities.safeClick(driver, badRequestLink);

    }

    @And("I click on Unauthorized link")
    public void iClickOnUnauthorizedLink() {
        By unauthorizedLink = By.id("unauthorized");
        Utilities.findElement(driver, unauthorizedLink, "center");
        Utilities.safeClick(driver, unauthorizedLink);

    }

    @And("I click on Forbidden link")
    public void iClickOnForbiddenLink() {
        By forbiddenLink = By.id("forbidden");
        Utilities.findElement(driver, forbiddenLink, "center");
        Utilities.safeClick(driver, forbiddenLink);
    }

    @And("I click on Not Found link")
    public void iClickOnNotFoundLink() {
        By noFoundLink = By.id("not-found");
        Utilities.findElement(driver, noFoundLink, "center");
        Utilities.safeClick(driver, noFoundLink);

        Utilities.validateLinksInSection(driver,"/html/body/main/div/div/div[2]");

        Utilities.switchToWindowByIndex(driver,2);
        driver.close();
        Utilities.switchToWindowByIndex(driver,1);
        driver.close();
        Utilities.switchToWindowByIndex(driver,0);


    }

    @Given("I am on Broken link image and link")
    public void iAmOnBrokenLinkImageAndLink() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By Buttons = By.xpath("//a[normalize-space()='Broken Links - Images']");
        Utilities.safeClick(driver,Buttons);
    }

    @Then("I validate image and link")
    public void iValidateImageAndLink() {
        Utilities.validateLinksAndImagesInSection(driver, "/html/body/main/div/div/div[2]");

    }


    @Given("I am on Upload and Download test page")
    public void iAmOnUploadAndDownloadTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By Buttons = By.xpath("//a[normalize-space()='Upload and Download']");
        Utilities.safeClick(driver,Buttons);
    }

    @When("I click on download button")
    public void iClickOnDownloadButton() {
        By downloadButton = By.id("downloadButton");
        Utilities.safeClick(driver,downloadButton);
        Utilities.pressKeyOnly(driver,downloadButton, Keys.ENTER);
    }


    @Then("I click on upload button")
    public void iClickOnUploadButton() {
        WebElement uploadButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("uploadFile")));
        uploadButton.sendKeys("C:\\Users\\Ray\\IdeaProjects\\tutorialspoint\\src\\test\\resources\\material\\da.txt");

    }


    @Given("I am on Dynamic Properties test page")
    public void iAmOnDynamicPropertiesTestPage() {
        By elementsDrop = By.xpath("//button[normalize-space()='Elements']");
        Utilities.clickMultipleTimes(driver,elementsDrop,2, 1000);

        By Buttons = By.xpath("//a[normalize-space()='Dynamic Properties']");
        Utilities.safeClick(driver,Buttons);
    }

    @When("I wait {int} second until button interactable")
    public void iWaitSecondUntilButtonInteractable(int arg0) {
        By color = By.id("colorChange");
        Utilities.clickMultipleTimes(driver,color,10,400);
        By visible  = By.id("visibleAfter");
        Utilities.waitForElementVisible(driver,visible,10000);
        Utilities.getElementTextSafe(driver,visible,"Message Not Found");
    }


    @And("I fill fake email")
    public void iFillFakeEmail() {
        By emailBox = By.id("email");
        String email = Utilities.generateEmail();
        Utilities.sendKeys(driver, emailBox, email);
    }


}
