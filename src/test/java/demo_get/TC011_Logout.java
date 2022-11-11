package demo_get;

import demo_post.RetryAnalyzer;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC011_Logout {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void createUser()
    {
        //base URI
        RestAssured.baseURI = "https://petstore.swagger.io";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();


        //Response object
        Response response = httpRequest.request(Method.GET,"/v2/user/logout");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);

        //type validation
        String type = response.jsonPath().get("type");
        System.out.println("type is " +type);
        Assert.assertEquals(type,"unknown");

        //message validation
        String message = response.jsonPath().get("message");
        System.out.println("message is " +message);
        Assert.assertEquals(message,"ok");

    }
}

