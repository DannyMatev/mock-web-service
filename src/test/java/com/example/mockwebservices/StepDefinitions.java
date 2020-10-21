package com.example.mockwebservices;

import com.example.mockwebservices.controller.EntityController;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefinitions extends CucumberSpringConfiguraton {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);

    @When("The client calls {string}")
    public void theClientExecutesGetRequest(String path) throws Throwable {
        executeGet("http://localhost:8080"+path);
    }

    @Then("The client receives status code of {int}")
    public void theClientRecievesStatusCode(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = latestResponse.getHttpStatus();
        LOGGER.info("Current status is: "+currentStatusCode.value());

        assertThat(currentStatusCode.value(), is(statusCode));

    }
}
