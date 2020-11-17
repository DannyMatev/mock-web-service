package com.example.mockwebservices;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features/mock-api.feature"})
public class CucumberIntegrationTest {

}
