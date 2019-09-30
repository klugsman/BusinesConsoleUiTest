package com.optal.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestNewCardPage  extends PageBase{

    private static final Logger logger = LoggerFactory.getLogger("RequestNewCardPage");

    @FindBy(id = "cardProfile")
    private WebElementFacade cardProfile = null;

    @FindBy(id = "currencies")
    private WebElementFacade currencies = null;

    public void fillNewCardRequest(List<List<String>> data) {
        String singleCard = data.get(1).get(1);
        cardProfile.selectByVisibleText(singleCard);
        currencies.selectByVisibleText(data.get(2).get(1));

    }
}
