package demo_get;

import demo_post.RetryAnalyzer;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC010_Login {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void createUser()
    {
        //base URI
        RestAssured.baseURI = "https://petstore.swagger.io";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();


        //Response object
        Response response = httpRequest.request(Method.GET,"/v2/user/login?username=Ashish123&password=abcd");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);


    }
}

