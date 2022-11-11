package demo_put;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_UpdateExistingRecord {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void updatesTheExistingRecord()
    {
        //base URI
        RestAssured.baseURI = "https://dummy.restapiexample.com";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //sending request
        JSONObject requestParams = new JSONObject();

        requestParams.put("name","test1");
        requestParams.put("salary","123");
        requestParams.put("age","23");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString()); //attach above data to request

        //Response object
        Response response = httpRequest.request(Method.PUT,"/api/v1/update/21");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);

        //status validation
        String status = response.jsonPath().get("status");
        System.out.println("status is " +status);
        Assert.assertEquals(status,"success");

        //message validation
        String message = response.jsonPath().get("message");
        System.out.println("message is " +message);
        Assert.assertEquals(message,"Successfully! Record has been updated.");

    }
}
