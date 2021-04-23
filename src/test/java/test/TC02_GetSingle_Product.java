package test;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TC02_GetSingle_Product extends BaseTest {

    @BeforeClass
    void getAll() throws InterruptedException, FileNotFoundException {
        JSONParser jsonParser = new JSONParser();
        try {
            id = jsonParser.parse(new FileReader("C:\\Users\\Imam Setya Utama\\TestRestApi\\id.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Started TC02 Get All");
        RestAssured.baseURI = "https://gorest.co.in/public-api/products";
        httpRequest = RestAssured.given().header("Authorization", "Bearer " + token);
        response = httpRequest.request(Method.GET, "/?id=" + id);
        Thread.sleep(3);

    }

    @Test
    void checkResponseBody() throws IOException {
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
