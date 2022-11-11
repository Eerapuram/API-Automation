package demo_post;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_DemoCreate {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void createUserDetails()
    {
        //base URI
        RestAssured.baseURI = "https://reqres.in";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //sending request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name","Ashish");
        requestParams.put("job","software");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString()); //attach above data to request

        //Response object
        Response response = httpRequest.request(Method.POST,"/api/users");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 201);

        //name validation
        String name = response.jsonPath().get("name");
        System.out.println("name is " +name);
        Assert.assertEquals(name,"Ashish");

    }
}
