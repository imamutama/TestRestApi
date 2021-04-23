package test;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

public class TC01_GetAll_Product extends BaseTest {

    @BeforeClass
    void getAll() throws InterruptedException {
        logger.info("Started TC001 Get All");
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        httpRequest = RestAssured.given().header("Authorization", "Bearer " + token);
        response = httpRequest.request(Method.GET, "/products");
        Thread.sleep(3);
    }

    @Test
    void checkResponseBody() {
        logger.info("Checking Response Body");
        String responseBoody = response.getBody().asString();
        logger.info("Response Body =>" + responseBoody);
        Assert.assertTrue(responseBoody != null);
    }

    @Test
    void checkResponseCode() {
        logger.info("Checking Status Code");
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }
}
