package hooks;

import com.aventstack.extentreports.ExtentTest;
import helpers.RequestAndResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.ExtentReportUtil;

import java.lang.reflect.Method;
import setup.ApiBase;

import static java.lang.reflect.Method.*;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import com.aventstack.extentreports.ExtentTest;

import java.lang.reflect.Method;

public class TestHooks {
    private static final ThreadLocal<RequestAndResponse> requestAndResponseHolder = new ThreadLocal<>();

    // Initialize ExtentReports before the suite starts
    @BeforeSuite
    public void beforeSuite() {
        ExtentReportUtil.initialize();
        System.out.println("ExtentReports initialized for the suite.");
    }

    // Close ExtentReports after the suite ends
    @AfterSuite
    public void afterSuite() {
        ExtentReportUtil.close();
        System.out.println("ExtentReports closed for the suite.");
    }

    // Before each test method starts
    @BeforeMethod
    public void beforeMethod(Method method) {
        RestAssured.baseURI = "https://dummyjson.com/"; // Set the base URL for the API
        ExtentReportUtil.startTest(method.getName());  // Start the test
        System.out.println("Started test: " + method.getName());
    }

    // After each test method ends
    @AfterMethod
    public void afterMethod(ITestResult result) {
        ExtentTest extentTest = ExtentReportUtil.getTest();

        // Log the result of the test case
        if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportUtil.logTestPass(result.getName() + " Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReportUtil.logTestFail(result.getThrowable().toString());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportUtil.logTestInfo(result.getName() + " Skipped");
        }

        // Attach request and response details to the report
        RequestAndResponse requestAndResponse = requestAndResponseHolder.get();
        if (requestAndResponse != null) {
            Response response = requestAndResponse.getResponse();
            RequestSpecification requestSpec = requestAndResponse.getRequestSpecification();

            // Log Request Details
            extentTest.info("**Request Details**");

            // Log the Request URL using baseURI and path
            String requestUrl = RestAssured.baseURI ; // Adjust based on actual endpoint
            extentTest.info("Request URL: " + requestUrl);
            extentTest.info("Request Method: " + requestAndResponse.getRequestMethod());
/*
            // Log headers
            ApiBase.requestHeaders.get().forEach((key, value) -> {
                extentTest.info("Request Header: " + key + " - " + value);
            });*/
/*

            if (requestSpec.getBody() != null) {
                extentTest.info("Request Body: " + requestSpec.getBody().toString());
            }
*/

            // Log Response Details
            extentTest.info("**Response Details**");
            extentTest.info("Response Status Code: " + response.getStatusCode());
            extentTest.info("Response Body: " + response.getBody().prettyPrint());
        }

        // Clean up ThreadLocal
        requestAndResponseHolder.remove();

        // End the current test
        ExtentReportUtil.endTest();
    }

    // Set the request and response for logging purposes
    public static void setRequestAndResponse(RequestSpecification requestSpec, Response response, String method) {
        requestAndResponseHolder.set(new RequestAndResponse(requestSpec, response, method));
    }
}
