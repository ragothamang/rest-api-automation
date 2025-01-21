package validators;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

public  final class ResponseValidator {

    // Private constructor to prevent instantiation
    private ResponseValidator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Validates the given response against a JSON Schema.
     *
     * @param response    The RestAssured Response to validate.
     * @param schemaPath  The file path to the JSON Schema.
     * @return true if the response matches the schema; false otherwise.
     */
    public static Boolean validateResponseSchema(Response response, String schemaPath) {
        try {
            response
                    .then()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
            return true; // Validation passed
        } catch (AssertionError e) {
            // Validation failed
            System.err.println("Schema validation failed: " + e.getMessage());
            return false;
        }
    }
}
