package hooks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestHooks {

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
}
