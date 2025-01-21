package setup;

import hooks.TestHooks;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiBase extends TestHooks {
//    public static Response get(String url) {
//        return given().when().get(url);
//    }
    // Common RequestSpecification with headers
    public static RequestSpecification requestSpec() {
        return given()
                .header("Content-Type", "application/json");
    }

    public static Response get(String url) {
        return requestSpec().when().get(url);
    }

    public static Response post(String url, String payload) {
        return requestSpec()
                .body(payload)
                .post(url);
    }

    public static Response put(String url, String payload) {
        return requestSpec()
                .body(payload)
                .put(url);
    }

    public static Response patch(String url, String payload) {
        return requestSpec()
                .body(payload)
                .patch(url);
    }

    public static Response delete(String url) {
        return requestSpec().delete(url);
    }
}
