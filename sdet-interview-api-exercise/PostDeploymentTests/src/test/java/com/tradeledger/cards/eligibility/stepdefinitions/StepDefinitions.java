package com.tradeledger.cards.eligibility.stepdefinitions;

import com.tradeledger.cards.eligibility.pojo.models.response.EligibleCards;
import com.tradeledger.cards.eligibility.utilities.ApiResources;
import com.tradeledger.cards.eligibility.utilities.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.Assert;



import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StepDefinitions extends Utility {

    ResponseSpecification responseSpec;
    Response response;

    
    @Given("applicant details with {string} {string} {string} if server is down")
    public void applicant_details_with_if_server_is_down(String name, String email, String address) throws IOException, IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Then("the API call get success with status code {int}")
    public void the_API_call_get_success_with_status_code(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the API call get bad request with status code {int}")
    public void the_API_call_get_bad_request_with_status_code(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the API call get unauthorized with status code {int}")
    public void the_API_call_get_unauthorized_with_status_code(int statusCode) {
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    
    @Then("the API call get service unavailable with status code {int}")
    public void the_API_call_get_service_unavailable_with_status_code(int statusCode) {
        if (response == null) {
            Assertions.assertEquals(statusCode, 503);
        }

        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    @Given("applicant details with {string} {string} {string} which are not null")
    public void applicant_details_with_which_are_not_null(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    
    @When("{string} is called with {string} http request")
    public void is_called_with_http_request(String resource, String method) {
        ApiResources resourceAPI = ApiResources.valueOf(resource);
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();

        try {
            if(method.equalsIgnoreCase("POST"))
                response = requestSpec.when().post(resourceAPI.getResource());
        }
        catch(Exception exception) {
            if (exception.getClass() == ConnectException.class) {
                response = null;
            }
            else {
                throw exception;
            }
        }
    }

    @Then("for given applicant with {string} it should show eligible cards")
    public void for_given_applicant_with_it_should_show_eligible_cards(
            String name, List<List<String>> expectedCardsRawList) throws Throwable {

        String srcName = handleTrailingSpaceAndNullCheck(name);
        String applicantName = (srcName != null) ?  srcName.trim() : "";

        // Find expected record by name in expected results datatable
        List<String> expectedRecordRow = expectedCardsRawList.stream()
                .filter(r -> r.get(0).trim().equalsIgnoreCase(applicantName))
                .findFirst()
                .orElse(null);

        // Check whether expected record exists (or) not?
        // If exists then also check second column string value is null (or) empty?
        List<String> expectedCardsList = new ArrayList<>();
        if (expectedRecordRow != null) {
            String expectedCards = expectedRecordRow.get(1);
            if ((expectedCards != null) && !expectedCards.trim().isEmpty()) {
                expectedCardsList = Arrays.asList(expectedCards.split(","));
            }
        }

        // Get Actual eligible cards results from response
        EligibleCards eligibleCards = response.getBody().as(EligibleCards.class);
        List<String> actualCardsList = Arrays.asList(eligibleCards.getEligibleCards());

        Assertions.assertEquals(expectedCardsList.size(), actualCardsList.size());

        if(applicantName.equalsIgnoreCase("Boris") ||
                applicantName.equalsIgnoreCase("Angela") ||
                applicantName.equalsIgnoreCase("Theresa")) {

            expectedCardsList.stream().forEach(expCard ->
                    Assertions.assertTrue(actualCardsList.stream().anyMatch(
                            actCard -> actCard.trim().equals(expCard.trim()))));

            /* If we need to validate exact order of response
            IntStream.range(0, expectedCardsList.size())
                    .forEach(index -> Assertions.assertEquals(expectedCardsList.get(index), actualCardsList.get(index)));*/
        }
    }

    @Given("applicant details with {string} {string} {string} which are having trailing spaces")
    public void applicant_details_with_which_are_having_trailing_spaces(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Given("applicant details with {string} {string} {string} which are null")
    public void applicant_details_with_which_are_null(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Given("applicant details with {string} {string} {string} which are invalid integer data type")
    public void applicant_details_with_which_are_invalid_integer_data_type(String name, String email, String address) throws IOException {
        mapInValidDataTypePayloadToRequest(name, email, address);
    }

    @Given("applicant details with {string} {string} {string} with invalid name key in request body")
    public void applicant_details_with_with_invalid_name_key_in_request_body(String username, String email, String address) throws IOException {
        mapInValidKeyPayloadToRequest(username, email, address);
    }

    @Given("empty request body without applicant details")
    public void empty_request_body_without_applicant_details() throws IOException {
        mapRequestWithoutPayloadInBody();
    }

    @Given("applicant details with {string} {string} {string} which are empty strings")
    public void applicant_details_with_which_are_empty_strings(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Given("applicant details with {string} {string} {string} where email address is valid")
    public void applicant_details_with_where_email_address_is_valid(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Given("applicant details with {string} {string} {string} where email address is invalid")
    public void applicant_details_with_where_email_address_is_invalid(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Given("applicant details with {string} {string} {string} where email is string and not null")
    public void applicant_details_with_where_email_is_string_and_not_null(String name, String email, String address) throws IOException {
        mapValidPayloadToRequest(name, email, address);
    }

    @Then("for applicant {string} address API call should return status code {int} if email is valid else {int}")
    public void for_applicant_address_API_call_should_return_status_code_if_email_is_valid_else(
            String email, int successStatusCode, int failedStatusCode) {
        String applicantEmail = handleTrailingSpaceAndNullCheck(email);
        int expectedStatusCode = isValidEmailAddress(applicantEmail) ? successStatusCode : failedStatusCode;
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Given("provided valid {string} and {string} in request header")
    public void provided_valid_and_in_request_header(String apiKeyName, String apiKeyValue) {
        requestSpec.header(apiKeyName, apiKeyValue);
    }

    @Given("provided invalid {string} and {string} in request header")
    public void provided_invalid_and_in_request_header(String apiKeyName, String apiKeyValue) {
        requestSpec.header(apiKeyName, apiKeyValue);
    }

    @Given("provided {string} and {string} in request header")
    public void provided_and_in_request_header(String apiKeyName, String apiKeyValue) {
        requestSpec.header(apiKeyName, apiKeyValue);
    }

    @Then("the API call should return success code {int} if {string} and {string} is valid else {int}")
    public void the_API_call_should_return_success_code_if_and_is_valid_else(
            int successStatusCode, String apiKeyName, String apiKeyValue, int failedStatusCode) {
        String actualApiKeyName = handleTrailingSpaceAndNullCheck(apiKeyName);
        String actualApiKeyValue = handleTrailingSpaceAndNullCheck(apiKeyValue);

        int expectedStatusCode = Secrets.apiKeyName.equals(actualApiKeyName) &&
                Secrets.apiKeyValue.equals(actualApiKeyValue) ? successStatusCode : failedStatusCode;

        Assertions.assertEquals(expectedStatusCode, response.getStatusCode());
    }
}
