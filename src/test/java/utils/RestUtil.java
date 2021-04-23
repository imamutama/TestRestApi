package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtil {

    public static String name(){
        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("Test"+generatedString);
    }
    public static String price(){
        String generatedString = RandomStringUtils.randomNumeric(5);
        return ("Test"+generatedString);
    }
}
