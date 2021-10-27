package cucumber.stepDefs;

import cucumber.stepDefs.selenium.SeleniumWebDriverConfig;
import io.cucumber.java.en.Then;

public class MyStepdefs {

    SeleniumWebDriverConfig myDriver;

    public MyStepdefs() {
        myDriver = new SeleniumWebDriverConfig();
    }

    @io.cucumber.java.en.Given("the client is on the main page")
    public void theClientIsOnTheMainPage() {
    }

    @io.cucumber.java.en.When("the client searches for a subscription")
    public void theClientSearchesForASubscription(int arg0) {
        myDriver.driver.findElement(By.xpath("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-session/mat-drawer-container/mat-drawer/div/button[2]")).click();


    }


    @Then("the client receives subscription  details")
    public void theClientReceivesSubscriptionDetails() {
        WebDriverWait wait = new WebDriverWait(myDriver.driver, 10);
        WebElement btnsubscription = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnsubscription")));
    }
}
