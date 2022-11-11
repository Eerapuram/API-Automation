package demo_put;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_UpdateExistingUser {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void updatesTheExistingRecord()
    {
        //base URI
        RestAssured.baseURI = "https://petstore.swagger.io";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //sending request
        JSONObject requestParams = new JSONObject();

        requestParams.put("id","5");
        requestParams.put("username", "Ashish123");
        requestParams.put("firstName", "kumar");
        requestParams.put("lastName", "ashish");
        requestParams.put("email", "123@gmail.com");
        requestParams.put("password", "abcd");
        requestParams.put("phone", "123456");
        requestParams.put("userStatus", 5);
        requestParams.put("salary","123");
        requestParams.put("age","23");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString()); //attach above data to request

        //Response object
        Response response = httpRequest.request(Method.PUT,"/v2/user/Ashish123");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);

        //status validation
        String type = response.jsonPath().get("type");
        System.out.println("type is " +type);
        Assert.assertEquals(type,"unknown");

        //message validation
        String message = response.jsonPath().get("message");
        System.out.println("message is " +message);
        Assert.assertEquals(message,"5");

        //header validation
        String TransferEncoding = response.header("Transfer-Encoding");
        System.out.println("Transfer-Encoding is " +TransferEncoding);
        Assert.assertEquals(TransferEncoding,"chunked");

    }
}
