package com.optal.steps;

import com.optal.support.WorldHelper;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;

public class MyStepdefs {

    private WorldHelper helper;

    public MyStepdefs(WorldHelper helper) {
        this.helper = helper;
    }

    @Then("^lvdlgjls e the text \"([^\"]*)\"$")
    public void lvdlgjlsETheText(String arg0) throws Throwable {
        //helper.getLandingPage().requestSingleCard();
    }
}
