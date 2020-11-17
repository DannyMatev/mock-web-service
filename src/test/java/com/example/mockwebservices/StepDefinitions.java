package com.example.mockwebservices;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("test")
public class StepDefinitions extends CucumberSpringConfiguraton {
    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);


    @When("The client makes a GET call to {string}")
    public void theClientExecutesGetRequest(String path) throws Throwable {
        executeGet("http://localhost:8080" + path);
    }

    @When("The client makes a POST call to {string} with body:")
    public void theClientExecutesPostRequest(String path, String payload) throws Throwable {
        executePost("http://localhost:8080" + path, payload);
    }

    @Then("The client receives status code of {int}")
    public void theClientRecievesStatusCode(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = responseEntity.getStatusCode();
        assertThat(currentStatusCode.value(), is(statusCode));
    }

    @And("The client receives response as following:")
    public void theClientRecievesResponse(String expectedResponse) throws Throwable {
        String responseJson = responseEntity.getBody();

        assertThat(objectMapper.readTree(responseJson).toPrettyString(),
                is(objectMapper.readTree(expectedResponse).toPrettyString()));
    }
}
