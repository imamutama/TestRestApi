package test;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC05_Delete_Product extends BaseTest {
    @BeforeClass
    void deleteProduct() throws InterruptedException {
        logger.info("Started TC005 Delete Product");
        RestAssured.baseURI = "https://gorest.co.in/public-api/products";
        httpRequest = RestAssured.given().header("Authorization", "Bearer " + token);
        response = httpRequest.request(Method.DELETE, "/" + id);
        Thread.sleep(3);
    }
    @Test
    void checkResponseBody() {
        logger.info("Checking Response Body");
        String responseBoody = response.getBody().asString();
        logger.info("Response Body =>" + responseBoody);
        Assert.assertEquals(responseBoody.contains("Resource not found"), true);
    }

    @Test
    void checkResponseCode() {
        logger.info("Checking Status Code");
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, 200);
    }
}
