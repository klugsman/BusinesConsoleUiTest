package com.optal.steps;

import com.optal.pages.BasePage;
import com.optal.pages.LandingPage;
import com.optal.pages.RequestNewCardPage;
import com.optal.support.WorldHelper;
import com.optal.utilities.TestData;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RequestSingleCardSteps {

    private WorldHelper helper;
    private LandingPage landingPage;
    private RequestNewCardPage requestNewCardPage;

    public RequestSingleCardSteps(WorldHelper helper) {
        this.helper = helper;
    }


    @Given("^I am on the Account Home Page$")
    public void iAmOnTheAccountHomePage() throws Throwable {
        String username = TestData.getValue("client username");
        String password = TestData.getValue("client password");
        landingPage = helper.getBasePage().loginWith(username, password);
    }

    @When("^I request for a single card$")
    public void iRequestForASingleCard() throws Throwable {
        //requestNewCardPage = landingPage.requestSingleCard();
    }

    @When("^I select the following options for the new card$")
    public void iSelectTheFollowingOptionsForTheNewCard(DataTable dataTable) throws Throwable {
        List<List<String>> data = dataTable.raw();
        requestNewCardPage.fillNewCardRequest(data);
    }

    @Then("^I should be able to create a new card with the details below:$")
    public void iShouldBeAbleToCreateANewCardWithTheDetailsBelow(DataTable dataTable) throws Throwable {
        List<List<String>> data = dataTable.raw();
    }

}
