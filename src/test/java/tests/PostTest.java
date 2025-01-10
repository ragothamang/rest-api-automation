package tests;

import applicationApi.PostsAPI;
import hooks.TestHooks;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTest extends TestHooks {
    @Test
    public void test_get_all_posts(){
        PostsAPI postApi = new PostsAPI();
        Response response = postApi.getAllPosts();
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
