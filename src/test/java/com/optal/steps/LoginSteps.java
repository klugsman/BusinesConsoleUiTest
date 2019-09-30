package com.optal.steps;

import com.optal.utilities.TestData;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.optal.pages.BasePage;
import com.optal.pages.LandingPage;
import com.optal.support.WorldHelper;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginSteps {
    private static final Logger logger = LoggerFactory.getLogger("LoginSteps");

    @Steps
    private WorldHelper helper;

    @Steps
    private BasePage basePage;

    @Steps
    private LandingPage landingPage;

   /*public LoginSteps(WorldHelper helper) {
        this.helper = helper;
    }*/





    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() throws Throwable {
        basePage  = helper.getBasePage();
    }

    @When("^I login with ([^\"]*) & \"([^\"]*)\"$")
    public void iLoginWith(String username, String password) throws Throwable {
        username = TestData.getValue(username);
        password = TestData.getValue(password);
        landingPage = basePage.loginWith("ksgjgkdk", password);
    }



    @Then("^I should be able to see the text \"([^\"]*)\"$")
    public void iShouldBeAbleToSeeTheText(String loginMessage) throws Throwable {
        loginMessage = TestData.getValue(loginMessage);
        //assertThat(landingPage.validateLogin(loginMessage), is(false));
    }
}
