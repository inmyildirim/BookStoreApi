package demoqa.step_definitions;

import demoqa.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static io.restassured.RestAssured.*;

public class CreateUserSteps {
    Response response;
    String requestUsername;
    String requestPassword;
    String responseUserID;
    File outFile;
    PrintWriter outPut;


    @When("User sends a POST Request to create user end point")
    public void user_sends_a_POST_Request_to_create_user_end_point() {
        //body part of request
        requestUsername = ConfigurationReader.get("userName");
        requestPassword = ConfigurationReader.get("password");

        Map<String,String> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", requestUsername);
        requestBody.put("password", requestPassword);

        //endpoint of request
        baseURI = ConfigurationReader.get("baseUrl");
        basePath = ConfigurationReader.get("apiUser");

        //creating request to API
        response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestBody)
                .when().post();
    }

    @When("User captures status code userID username information")
    public void user_captures_status_code_userID_username_information() throws FileNotFoundException {
        //we are deserializing response object to get userID
        Map<Object,Object> responseMap = response.as(Map.class);
        responseUserID = (String) responseMap.get("userID");
        //writing userID to a regular resources file
        //defining a file: we need to provide path of the file
        outFile = new File("C:\\Users\\Mehmet\\IdeaProjects\\ApiUpSkillProject\\src\\test\\resources\\features\\userID.out");
        if (outFile.exists()){
            outFile.delete();
        }
        outPut = new PrintWriter(outFile);
        outPut.println(responseUserID);
        outPut.close(); // this step crucial to actually finalize writing function
    }

    @Then("Verify status code username and UserID is NOT null")
    public void verify_status_code_username_and_UserID_is_NOT_null() throws FileNotFoundException {

        //read the file for userID
        outFile = new File("C:\\Users\\Mehmet\\IdeaProjects\\ApiUpSkillProject\\src\\test\\resources\\features\\userID.out");

        Scanner scan = new Scanner(outFile);
        String userID = scan.next();
        System.out.println("userID =" +userID);


    }
}
