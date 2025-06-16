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

public class Forms {
    private WebDriver driver = driverFactory.getDriver();
    private WebDriverWait wait = driverFactory.getWait();
    private Faker faker = driverFactory.getFaker();
    @Given("I am on the Forms Test page")
    public void iAmOnTheFormsTestPage() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
    }

    @Given("I fill first name with fake data")
    public void iFillFirstNameWithFakeData() {
        By nameBox = By.id("name");
        String name = Utilities.generateFullName();
        Utilities.sendKeys(driver,nameBox,name);
    }

    @And("I choose gender")
    public void iChooseGender() {
        By male = By.id("gender");
        Utilities.safeClick(driver,male);

        By female = By.xpath("//div[3]//div[1]//div[1]//div[2]//input[1]");
        Utilities.safeClick(driver,female);

        By other = By.xpath("//div[3]//div[1]//div[1]//div[3]//input[1]");
        Utilities.safeClick(driver,other);

        Utilities.safeClick(driver,male);
    }

    @And("I fill mobile number with fake data")
    public void iFillMobileNumberWithFakeData() {
        By number = By.id("mobile");
        String numberPhone = Utilities.generatePhoneNumber();
        Utilities.sendKeys(driver,number,numberPhone);

    }

    @And("I fill date of birth with fake data")
    public void iFillDateOfBirthWithFakeData() {
        By date = By.id("dob");
        Utilities.sendKeys(driver,date,"02/10/1991");
    }

    @And("I fill subject with {string}")
    public void iFillSubjectWith(String subject) {
        By subjectForm = By.id("subjects");
        Utilities.sendKeys(driver,subjectForm,subject);
    }

    @And("I choose hobbies")
    public void iChooseHobbies() {
        List<By> multipleHobbies = Arrays.asList(
                By.id("hobbies"),
                By.xpath("//div[7]//div[1]//div[1]//div[2]//input[1]"),
                By.xpath("//div[7]//div[1]//div[1]//div[3]//input[1]")
        );
        Utilities.clickMultipleElements(driver,multipleHobbies);
    }

    @And("I uploaded picture")
    public void iUploadedPicture() {
        By uploadPict = By.id("picture");
        Utilities.sendKeys(driver,uploadPict,"C:\\Users\\Ray\\IdeaProjects\\tutorialspoint\\src\\test\\resources\\material\\sampleFile.jpeg");
    }

    @And("I fiil address with fake data")
    public void iFiilAddressWithFakeData() {
        By address = By.xpath("//textarea[@id='picture']");
        String fakeAddress = Utilities.generateAddress();
        Utilities.sendKeys(driver,address,fakeAddress);
    }

    @And("I choose state {string} and {string}")
    public void iChooseStateAnd(String state, String city) {
        By stateBox = By.id("state");
        Utilities.safeClick(driver,stateBox);
        Utilities.selectDropdownByVisibleText(driver,stateBox,state);

        By cityBox = By.id("city");
        Utilities.safeClick(driver,stateBox);
        Utilities.selectDropdownByVisibleText(driver,cityBox,city);
    }

    @Then("I click on submit practice form")
    public void iClickOnSubmitPracticeForm() {
        By submitForms = By.xpath("//input[@value='Login']");
        Utilities.safeClick(driver,submitForms);
    }
}
