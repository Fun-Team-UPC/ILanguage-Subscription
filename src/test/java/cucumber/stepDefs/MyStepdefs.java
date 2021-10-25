package cucumber.stepDefs;

import cucumber.stepDefs.selenium.SeleniumWebDriverConfig;

public class MyStepdefs {

    SeleniumWebDriverConfig myDriver;

    public MyStepdefs() {
        myDriver = new SeleniumWebDriverConfig();
    }

    @io.cucumber.java.en.Given("the client is on the main page")
    public void theClientIsOnTheMainPage() {
    }

    @io.cucumber.java.en.When("the client searches for a subscription {int}")
    public void theClientSearchesForASubscription(int arg0) {
    }

    @io.cucumber.java.en.Then("the client receives subscription {int} details")
    public void theClientReceivesSubscriptionDetails(int arg0) {
    }
}
