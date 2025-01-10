package setup;

import hooks.TestHooks;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiBase extends TestHooks {
    public static Response get(String url) {
        return given().when().get(url);
    }
}
