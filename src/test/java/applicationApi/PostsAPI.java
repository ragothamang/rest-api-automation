package applicationApi;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import setup.ApiBase;
import validators.ResponseValidator;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class PostsAPI extends ApiBase {
    private static final String POSTS_ENDPOINT = "/posts/";
    private static final String ADD_POST_ENDPOINT = "/posts/add";

    public static Response getAllPosts() {
        return get(POSTS_ENDPOINT);
    }

    public static Response getPostById(String id) {
        return get(POSTS_ENDPOINT + id);
    }

    public static Response createPost(String payload) {
        return post(ADD_POST_ENDPOINT, payload);
    }

    public static Response updatePost(int postId, String payload) {
        return put(POSTS_ENDPOINT + postId, payload);
    }

    public static Response patchPost(int postId, String payload) {
        return patch(POSTS_ENDPOINT+ postId, payload);
    }

    public static Response deletePost(int postId) {
        return delete(POSTS_ENDPOINT + postId);
    }

    public static Boolean isValidResponseForGetAllPosts(Response response, String schemaFilePath) {
        String fileName = "src/test/resources/" + schemaFilePath;
        return ResponseValidator.validateResponseSchema(response, fileName);
    }

    /*
    public static Response getAllPosts() {
        return get("/posts");
    }

    public static Response getPostById(String id) {
        return get("/posts/" + id);
    }

    public static Response createPost( String payload) {
        return
//                RestAssured.
                given()
                .header("Content-Type", "application/json")
                .body(payload)
                        .post("posts/add");
//                .post(BASE_URL);
    }

    public static Response updatePost(int postId, String payload) {
        return
                //RestAssured
                given()
                .header("Content-Type", "application/json")
                .body(payload)
                .put("/posts/"+postId);
//                .put(BASE_URL + "/" + postId);
    }

    public static Response patchPost(int postId, String payload) {
        return
//                RestAssured.
                given()
                .header("Content-Type", "application/json")
                .body(payload).patch("/posts/"+postId);

//                .patch(BASE_URL + "/" + postId);
    }

    public static Response deletePost(int postId) {
        return
//                RestAssured
                given()
                .header("Content-Type", "application/json")
                .delete("/posts/" + postId);
    }


    public static Boolean isValidResponseForGetAllPosts(Response response, String schemaFilePath){
        String fileName = "src/test/resources/"+schemaFilePath;
        return ResponseValidator.validateResponseSchema(response, fileName);
    }
*/

}
