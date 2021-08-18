package com.tradeledger.cards.eligibility.cucumber.options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/tradeledger/cards/eligibility/features/validations.feature"},
        /*tags = {"@TestThree"},*/
        glue = {"com.tradeledger.cards.eligibility.stepdefinitions" })
/*@Cucumber*/
public class RunCucumberTest {
}