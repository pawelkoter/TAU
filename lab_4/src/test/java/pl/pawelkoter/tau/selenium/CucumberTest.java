package pl.pawelkoter.tau.selenium;

import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;

@RunWith( Cucumber.class )
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        features = "classpath:cucumber"
)
public class CucumberTest {
}