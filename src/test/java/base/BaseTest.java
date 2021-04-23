package base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import org.apache.log4j.Logger;

import java.util.List;

public class BaseTest {

    public static RequestSpecification httpRequest;
    public static Response response;
    public Logger logger;
    public String token = "72e7f2a58d17f235194c7e348a71e07d1b7cd470b0c60940bbb953a0376f593e";
    public Object id;

    @BeforeClass
    public void setup(){
        logger = Logger.getLogger("LoggerRestAPI");
        PropertyConfigurator.configure("log4j.properties");
        logger.setLevel(Level.DEBUG);
    }
}
