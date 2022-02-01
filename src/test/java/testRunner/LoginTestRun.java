package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)            //Test runner can be reused for other tests
@CucumberOptions(
        features = ".//Features/Customers.feature",
        glue="stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty","html:test-output"}   //,
        // tags= "@sanity and @regression"  //tags will only execute specific tags in the test runner, not working rn for some reason
)

public class LoginTestRun {

}
