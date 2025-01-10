package tests;

import applicationApi.PostsAPI;
import hooks.TestHooks;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTest extends TestHooks {
    @Test
    public void test_get_all_posts(){
        Response response = PostsAPI.getAllPosts();
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void test_get_post_by_id(){
        Response response = PostsAPI.getPostById("1");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
