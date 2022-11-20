package demoqa.pages;

import demoqa.utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;
import io.cucumber.java.Before;

public class CreateRequestBasePage {

    //endpoint of request

    @Before
    public void setUpRequest(){
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiUser");
    }


}
