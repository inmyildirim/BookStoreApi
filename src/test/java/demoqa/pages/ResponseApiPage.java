package demoqa.pages;

import demoqa.utilities.ConfigurationReader;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseApiPage {

    public Map<String,String> getRequestBody(){
        String requestUsername = ConfigurationReader.get("userName");
        String requestPassword = ConfigurationReader.get("password");

        Map<String,String> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", requestUsername);
        requestBody.put("password", requestPassword);
        return requestBody;
    }

}
