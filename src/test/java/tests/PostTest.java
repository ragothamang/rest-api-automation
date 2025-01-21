package tests;

import applicationApi.PostsAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;
import hooks.TestHooks;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Post;

import java.util.Arrays;
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

System.out.println("response schema isValid : "+ PostsAPI.isValidResponseForGetAllPosts(response, "posts_response_schema.json"));
    }
    @Test
    public void test_create_Post() {
        String payloadString = """
                {
                    "title": "rg name 1",
                    "userId": 200
                }
                """;
        Post post = new Post();
        post.setId(251);
        post.setTitle("The majestic waterfall thundered down the mountainside");
        post.setBody("Its waters cascading in a frothy torrent, sending mist into the air. Rainbows danced in the spray, adding to the spectacle of nature's power. It was a place where one could feel the heartbeat of the earth, a reminder of the awe-inspiring beauty of the natural world.");
        post.setTags(Arrays.asList("waterfall", "majestic", "nature"));

        Post.Reactions reactions = new Post.Reactions();
        reactions.setLikes(703);
        reactions.setDislikes(33);
        post.setReactions(reactions);

        post.setViews(867);
        post.setUserId(187);

        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload;
        try {
            jsonPayload = mapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        Response response   = PostsAPI.createPost(payloadString);
        Response response = PostsAPI.createPost(jsonPayload);
        Assert.assertEquals(response.getStatusCode(), 201, "201 As expected");
    }
    @Test
    public void test_patch_post() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode payload = mapper.createObjectNode();
        // Add fields to update dynamically
        payload.put("title", "Dynamic Title Update");
        payload.put("views", 1200);
        String payloadString;
        try {
            payloadString = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        Response response = PostsAPI.patchPost(251, payloadString);
        Assert.assertEquals(response.getStatusCode(), 200, "201 As expected");
    }

    @Test
    public void test_put_post() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode payload = mapper.createObjectNode();
        // Add fields to update dynamically
        payload.put("title", "Dynamic Title Update");
        payload.put("views", 1200);
        String payloadString;
        try {
            payloadString = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Send PUT request
        Response response = PostsAPI.updatePost(251, payloadString);

        // Print and validate response
        System.out.println("PUT Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "201 As expected");
    }
    @Test
    public void test_delete_post(){
        Response response = PostsAPI.deletePost(251);
        Assert.assertEquals(response.getStatusCode(), 200, "201 As expected");
    }
}
