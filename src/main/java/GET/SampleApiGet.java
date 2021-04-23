package GET;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleApiGet {
    @Test
    void getApiSample(){
        //Specify base URL
        RestAssured.baseURI="https://restapi.demoqa.com/utilities/weather/city";
        //RequestObject
        RequestSpecification httpRequest=RestAssured.given();
        //ResponseObject
        Response response =httpRequest.request(Method.GET,"Hyderabad");
        //print response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" +responseBody);
        //status code
        int statuscode = response.getStatusCode();
        System.out.println("Status Code is"+statuscode);
        Assert.assertEquals(statuscode,201);
        //status line verification
        String statusLine = response.getStatusLine();
        System.out.println("Status line is "+statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

    }
}
