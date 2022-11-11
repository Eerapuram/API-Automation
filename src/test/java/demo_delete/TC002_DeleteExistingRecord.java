package demo_delete;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_DeleteExistingRecord {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void deleteExistingRecord ()
    {
        //base URI
        RestAssured.baseURI = "https://dummy.restapiexample.com";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.DELETE,"/api/v1/delete/4");

        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response body is " +responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is " +statusCode);
        Assert.assertEquals(statusCode, 200);

        //status validation
        String data = response.jsonPath().get("data");
        System.out.println("data is " +data);
        Assert.assertEquals(data,"4");

        //message validation
        String message = response.jsonPath().get("message");
        System.out.println("message is " +message);
        Assert.assertEquals(message,"Successfully! Record has been deleted");


    }
}
