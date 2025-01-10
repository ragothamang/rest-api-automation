package applicationApi;

import io.restassured.response.Response;
import setup.ApiBase;

public class PostsAPI extends ApiBase {
    public static Response getAllPosts() {
        return get("/posts");
    }
}
