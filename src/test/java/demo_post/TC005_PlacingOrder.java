package demo_post;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_PlacingOrder {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void createUser()
    {
        //base URI
        RestAssured.baseURI = "https://petstore.swagger.io";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //sending request
        JSONObject requestParams = new JSONObject();


        requestParams.put("id", 11);
        requestParams.put("petId", 100);
        requestParams.put("quantity", 5);
        requestParams.put("shipDate", "2022-11-08T06:10:21.742+0000");
        requestParams.put("status", "placed");
        requestParams.put("complete", "true");

        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString()); //attach above data to request

        //Response object
        Response response = httpRequest.request(Method.POST,"/v2/store/order");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);


        //message validation
        String shipDate = response.jsonPath().get("shipDate");
        System.out.println("shipDate is " +shipDate);
        Assert.assertEquals(shipDate,"2022-11-08T06:10:21.742+0000");


    }
}
