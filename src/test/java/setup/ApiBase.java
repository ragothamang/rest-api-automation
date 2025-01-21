package setup;

import hooks.TestHooks;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiBase extends TestHooks {

    // Common RequestSpecification with headers
    public static RequestSpecification requestSpec() {
        return given()
                .header("Content-Type", "application/json");
    }

    // Perform a GET request
    public static Response get(String url) {
        RequestSpecification requestSpec = requestSpec();
        Response response = requestSpec.when().get(url);
        setRequestAndResponse(requestSpec, response,"GET");  // Store request and response for logging
        return response;
    }

    // Perform a POST request
    public static Response post(String url, String payload) {
        RequestSpecification requestSpec = requestSpec();
        Response response = requestSpec
                .body(payload)
                .post(url);
        setRequestAndResponse(requestSpec, response,"POST");  // Store request and response for logging
        return response;
    }

    // Perform a PUT request
    public static Response put(String url, String payload) {
        RequestSpecification requestSpec = requestSpec();
        Response response = requestSpec
                .body(payload)
                .put(url);
        setRequestAndResponse(requestSpec, response,"PUT");  // Store request and response for logging
        return response;
    }

    // Perform a PATCH request
    public static Response patch(String url, String payload) {
        RequestSpecification requestSpec = requestSpec();
        Response response = requestSpec
                .body(payload)
                .patch(url);
        setRequestAndResponse(requestSpec, response,"PATCH");  // Store request and response for logging
        return response;
    }

    // Perform a DELETE request
    public static Response delete(String url) {
        RequestSpecification requestSpec = requestSpec();
        Response response = requestSpec.delete(url);
        setRequestAndResponse(requestSpec, response,"DELETE");  // Store request and response for logging
        return response;
    }
}
