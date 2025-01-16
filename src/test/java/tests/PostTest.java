package tests;

import applicationApi.PostsAPI;
import com.jayway.jsonpath.JsonPath;
import hooks.TestHooks;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void learning_response_parsing(){
        Response response = PostsAPI.getAllPosts();
        System.out.println("Response Body "+ response.getBody().asString());
        System.out.println("Response Code "+ response.getStatusCode());
        System.out.println("******  Response  JSON handling  ****************");
        System.out.println("Response  JSON posts[0].title --> "+ response.jsonPath().get("posts[0].title"));
        System.out.println("Resp  JSON posts list size --> "+ response.jsonPath().getList("posts").size());
        System.out.println("Resp  JSON posts[0].tags (size)--> "+ response.jsonPath().getList("posts[0].tags").size());
//        List<String> titles =   JsonPath.read(response, ".posts[?(@.tags[*] == 'history')].title");
//        List<String> titles = JsonPath.read(response, ".posts[?(@.tags[?(@ == 'magical')])].title");
        String jsonPath = ".posts[?(@.tags[?(@ == 'magical')])]";
        List<Map<String, Object>> matchingPosts = JsonPath.read(response, jsonPath);

        System.out.println("Resp  titles --> "+ matchingPosts.size());
        System.out.println("Resp  JSON posts[0].title --> "+ response.jsonPath().getList("posts.title", String.class));
    }
}
