package automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"automation.hooks", "automation.stepDefs"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class runners {
}
