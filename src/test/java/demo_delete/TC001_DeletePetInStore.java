package demo_delete;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_DeletePetInStore {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    void deletePetInStore()
    {
        //base URI
        RestAssured.baseURI = "https://petstore.swagger.io";

        //Request object
        RequestSpecification httpRequest = RestAssured.given();

        //Response object
        Response response = httpRequest.request(Method.DELETE,"/v2/pet/9");

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
        Assert.assertEquals(message,"9");

        //header validation
        String Connection = response.header("Connection");
        System.out.println("Connection is " +Connection);
        Assert.assertEquals(Connection,"keep-alive");

    }
}
