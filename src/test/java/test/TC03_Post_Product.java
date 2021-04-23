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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TC03_Post_Product extends BaseTest {

    RequestSpecification httpRequest;
    Response response;

    String name = RestUtil.name();
    String description = "Ini product testing Axiata Digital Labs";
    String image = "test.jpg";
    int price = 1000;
    boolean status = false;

    @BeforeClass
    void createCampaignProduct() throws InterruptedException {
        logger.info("Started TC03 Create Campaign Product");
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("description", description);
        requestParams.put("image", image);
        requestParams.put("price", price);
        requestParams.put("status", status);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Authorization", "Bearer " + token);
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.request(Method.POST, "/products");
        JsonPath jsonPath = response.jsonPath();
        String id = jsonPath.getString("data.id");
        try (FileWriter file = new FileWriter("id.json")) {
            file.write(id.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println(id);
        Thread.sleep(5);

    }

    @Test
    void checkResponseBody() throws IOException {
        logger.info("Checking Response Body");
        String responseBoody = response.getBody().asString();
        logger.info("Response Body =>" + responseBoody);
        Assert.assertEquals(responseBoody.contains(name), true);
        Assert.assertEquals(responseBoody.contains(description), true);
        Assert.assertEquals(responseBoody.contains(image), true);
    }

    @Test
    void checkResponseCode() {
        logger.info("Checking Status Code");
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }
}
