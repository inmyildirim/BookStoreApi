package demoqa.step_definitions;

import demoqa.pages.ResponseApiPage;
import demoqa.utilities.BookStoreApiUtils;
import demoqa.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.*;

public class CreateUserSteps {
    Response response;
    String requestUsername;
    String requestPassword;
    String responseUserID;
    String responseUserName;
    int responseStatusCode;
    List<String> books;

    ResponseApiPage responsePage = new ResponseApiPage();


    @When("User sends a POST Request to create user end point")
    public void user_sends_a_POST_Request_to_create_user_end_point() {
        //body part of request
        requestUsername = ConfigurationReader.get("userName");
        requestPassword = ConfigurationReader.get("password");

        //creating request to API
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(responsePage.getRequestBody())
                .when().post();
    }

    @When("User captures status code, userID, username and books information")
    public void user_captures_status_code_userID_username_information() throws FileNotFoundException {
        //we are deserializing response object to get userID
        Map<Object, Object> responseMap = response.as(Map.class);
        responseUserID = (String) responseMap.get("userID");
        BookStoreApiUtils.storeInfoToFile(responseUserID);
        responseUserName = (String) responseMap.get("username");
        books = (List<String>) responseMap.get("books");

    }

    @Then("Verify status code username and UserID is NOT null")
    public void verify_status_code_username_and_UserID_is_NOT_null() throws FileNotFoundException {
        assertEquals(200,responseStatusCode);
        assertEquals(requestUsername,responseUserName);
        assertFalse(responseUserID == null);
    }

}
