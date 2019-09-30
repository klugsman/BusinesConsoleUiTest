package com.optal;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        dryRun = false 	//Does not run any code content in the step definition when it is true
        ,monochrome = true 	//Make text on the console readable

        ,features = {"src/test/resources/Features"}		//Packages where the feature files are located

        ,glue = {"com.optal"}  			//Package with Step Definitions and hooks
        ,tags = {"@test"}
)
public class RunnerCukesTest {}
