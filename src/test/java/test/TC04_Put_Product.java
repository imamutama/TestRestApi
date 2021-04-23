package test;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.RestUtil;

public class TC04_Put_Product extends BaseTest {

    RequestSpecification httpRequest;
    Response response;

    String name = "Testing API Axiata";
    String description = "Ini product testing Axiata Digital Labs";
    String image = "test.jpg";
    int price = 5000;
    boolean status = false;
    int id = 229;
    @BeforeClass
    void putCampaignProduct() throws InterruptedException {
        logger.info("Started TC04 Put Campaign Product");
        RestAssured.baseURI ="https://gorest.co.in/public-api";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name",name);
        requestParams.put("description",description);
        requestParams.put("image",image);
        requestParams.put("price",price);
        requestParams.put("status",status);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer "+token);
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.PUT,"/products/"+id);
        Thread.sleep(5);

    }
    @Test
    void checkResponseBody(){
        logger.info("Checking Response Body");
        String responseBoody = response.getBody().asString();
        logger.info("Response Body =>"+responseBoody);
        Assert.assertEquals(responseBoody.contains(name),true);
        Assert.assertEquals(responseBoody.contains(description),true);
        Assert.assertEquals(responseBoody.contains(image),true);
    }
    @Test
    void checkResponseCode(){
        logger.info("Checking Status Code");
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }

}
