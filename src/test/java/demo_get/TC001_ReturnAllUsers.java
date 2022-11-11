package demo_get;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_ReturnAllUsers {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void getAllUserDetails()
    {
        //base URI
        RestAssured.baseURI = "https://reqres.in/api";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.GET,"/users?page=2");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);

    }
}
