package automation.stepDefs;

import automation.utils.Utilities;
import automation.utils.driverFactory;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interaction {

    private WebDriver driver = driverFactory.getDriver();
    private WebDriverWait wait = driverFactory.getWait();
    private Faker faker = driverFactory.getFaker();

    @Given("I am on Interaction Test page")
    public void iAmOnInteractionTestPage() {
        driver.get("https://www.tutorialspoint.com/selenium/practice/sortable.php#");
    }

    @Given("I am on Sortable Test page")
    public void iAmOnSortableTestPage() {
        By interactionDrop = By.xpath("//button[normalize-space()='Interaction']");
        Utilities.safeClick(driver,interactionDrop);
        By sortableTest = By.xpath("//a[normalize-space()='Sortable']");
        Utilities.safeClick(driver,sortableTest);
    }

    @Then("I sort element on List tab")
    public void iSortElementOnListTab() {


        By list1 = By.xpath("//td[normalize-space()='1']");
        By list2 = By.xpath("//td[normalize-space()='2']");
        By list3 = By.xpath("//td[normalize-space()='3']");
        Utilities.dragAndDrop(driver,list2,list3);
        Utilities.dragAndDrop(driver,list2,list1);
        Utilities.dragAndDrop(driver,list1,list3);
    }

    @And("I sort element on Grid tab")
    public void iSortElementOnGridTab() {
        By gridTab =  By.xpath("//button[@id='nav-profile-tab']");
        Utilities.safeClick(driver,gridTab);
        By grid1 = By.xpath("//h3[normalize-space()='Thumbnail label 1']");
        By grid2 = By.xpath("//h3[normalize-space()='Thumbnail label 2']");
        By grid3 = By.xpath("//h3[normalize-space()='Thumbnail label 3']");
        By grid4 = By.xpath("//h3[normalize-space()='Thumbnail label 4']");
        By grid5 = By.xpath("//h3[normalize-space()='Thumbnail label 5']");
        By grid6 = By.xpath("//h3[normalize-space()='Thumbnail label 6']");

        Utilities.dragAndDrop(driver,grid1,grid6);
        Utilities.dragAndDrop(driver,grid2,grid3);
        Utilities.dragAndDrop(driver,grid4,grid5);
        Utilities.dragAndDrop(driver,grid2,grid3);
        Utilities.dragAndDrop(driver,grid6,grid3);
    }

    @Given("I am on Selectable Test page")
    public void iAmOnSelectableTestPage() {
        By interactionDrop = By.xpath("//button[normalize-space()='Interaction']");
        Utilities.safeClick(driver,interactionDrop);
        By sortableTest = By.xpath("//a[normalize-space()='Selectable']");
        Utilities.safeClick(driver,sortableTest);
    }

    @Then("I select element on List tab")
    public void iSelectElementOnListTab() {
        List<By> multipleElementList = Arrays.asList(
                By.xpath("//*[@id=\"nav-home\"]/ul/li[1]"),
                By.xpath("//*[@id=\"nav-home\"]/ul/li[2]"),
                By.xpath("//*[@id=\"nav-home\"]/ul/li[3]"),
                By.xpath("//*[@id=\"nav-home\"]/ul/li[4]"),
                By.xpath("//*[@id=\"nav-home\"]/ul/li[5]"),
                By.xpath("//*[@id=\"nav-home\"]/ul/li[6]")
        );
        Utilities.clickMultipleElements(driver,multipleElementList);
    }

    @And("I select element on Grid tab")
    public void iSelectElementOnGridTab() {
        By gridTab = By.xpath("//button[@id='nav-profile-tab']");
        Utilities.safeClick(driver, gridTab);

        List<By> multipleElementGrid = Arrays.asList(
                By.xpath("//*[@id=\"nav-profile\"]/ul/li[1]"),
                By.xpath("//*[@id=\"nav-profile\"]/ul/li[2]"),
                By.xpath("//*[@id=\"nav-profile\"]/ul/li[3]"),
                By.xpath("//*[@id=\"nav-profile\"]/ul/li[4]"),
                By.xpath("//*[@id=\"nav-profile\"]/ul/li[5]"),
                By.xpath("//*[@id=\"nav-profile\"]/ul/li[6]")
        );
        Utilities.clickMultipleElements(driver,multipleElementGrid);
    }

    @Given("I am on Droppable Test page")
    public void iAmOnDroppableTestPage() {
        By interactionDrop = By.xpath("//button[normalize-space()='Interaction']");
        Utilities.safeClick(driver,interactionDrop);
        By sortableTest = By.xpath("//a[normalize-space()='Droppable']");
        Utilities.safeClick(driver,sortableTest);
    }

    @Then("I drag and drop element on Simple tab")
    public void iDragAndDropElementOnSimpleTab() {
        By drag = By.xpath("//div[@id='draggable']");
        By drop = By.xpath("//div[@id='droppable']");
        Utilities.dragAndDrop(driver,drag,drop);
    }

    @And("I drag and drop element on Accept tab")
    public void iDragAndDropElementOnAcceptTab() {
        By acceptTab = By.xpath("//body//main//button[2]");
        Utilities.safeClick(driver,acceptTab);
        By drag = By.xpath("//div[@id='div2']");
        By drop = By.xpath("//div[@id='div1']");
        Utilities.dragAndDrop(driver,drag,drop);

    }


    @Given("I am on Draggable Test page")
    public void iAmOnDraggableTestPage() {
        By interactionDrop = By.xpath("//button[normalize-space()='Interaction']");
        Utilities.safeClick(driver,interactionDrop);
        By sortableTest = By.xpath("//a[normalize-space()='Dragabble']");
        Utilities.safeClick(driver,sortableTest);
    }

    @Then("I drag element on Simple tab")
    public void iDragElementOnSimpleTab() {
        WebElement draggable = driver.findElement(By.xpath("//div[@id='draggables']"));
        new Actions(driver)
                .clickAndHold(draggable)
                .moveByOffset(  120, 120)
                .release()
                .perform();

        Utilities.loading(1000);
    }

    @And("I drag element on Axis tab")
    public void iDragElementOnAxisTab() {
        By axisTab = By.xpath("//body//main//button[2]");
        Utilities.safeClick(driver,axisTab);

        WebElement draggablex = driver.findElement(By.xpath("//div[@id='div_element']"));

        new Actions(driver)
                .clickAndHold(draggablex)
                .moveByOffset(  120, 1)
                .release()
                .perform();

        WebElement draggabley = driver.findElement(By.xpath("//div[@id='div_elementy']"));

        new Actions(driver)
                .clickAndHold(draggabley)
                .moveByOffset(  1, 90)
                .release()
                .perform();

        Utilities.loading(1000);
    }

    @And("I drag element on Container tab")
    public void iDragElementOnContainerTab() {
        By containerTab = By.xpath("//body//main//button[3]");
        Utilities.safeClick(driver,containerTab);

        WebElement draggablex = driver.findElement(By.id("intro"));

        new Actions(driver)
                .clickAndHold(draggablex)
                .moveByOffset(  50, 50)
                .release()
                .perform();

    }
}
