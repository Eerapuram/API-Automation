package demo_post;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginSuccess {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void getUserDetails()
    {
        //base URI
        RestAssured.baseURI = "https://reqres.in";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //sending request
        JSONObject requestParams = new JSONObject();

        requestParams.put("email","eve.holt@reqres.in");
        requestParams.put("password","cityslicka");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString()); //attach above data to request

        //Response object
        Response response = httpRequest.request(Method.POST,"/api/login");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);

        //Token validation
        String token = response.jsonPath().get("token");
        System.out.println("Token is " +token);
        Assert.assertEquals(token,"QpwL5tke4Pnpja7X4");

    }
}
