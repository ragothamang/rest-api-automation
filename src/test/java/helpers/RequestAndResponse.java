package helpers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestAndResponse {
    private RequestSpecification requestSpecification;
    private Response response;
    private String requestMethod;  // Store HTTP method (GET, POST, etc.)

    public RequestAndResponse(RequestSpecification requestSpecification, Response response, String requestMethod) {
        this.requestSpecification = requestSpecification;
        this.response = response;
        this.requestMethod = requestMethod;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public Response getResponse() {
        return response;
    }

    public String getRequestMethod() {
        return requestMethod;
    }
}

