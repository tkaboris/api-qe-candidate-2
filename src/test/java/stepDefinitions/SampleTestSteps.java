package stepDefinitions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.junit.Assert;
import utilities.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utilities.Configuration.logger;

public class SampleTestSteps {

    private Response response;
    private JsonObject requestBody;

    @When("a GET call is made to {string}")
    public void aGETCallIsMadeTo(String url) {
       response = given()
            .header("Content-type", ContentType.JSON)
            .contentType(ContentType.JSON)
            .when()
            .get(url)
            .then()
            .extract().response();
    }

    @Then("a {int} status code is returned")
    public void aStatusCodeIsReturned(int statusCode) {
        Assert.assertEquals("Code matched to the requirements", response.statusCode(), statusCode);
    }
//--------------------------------------------
    @Given("a json payload {string}")
    public void aJsonPayload(String fileName)  {

        //extract the json file name
        //its going to split the string in an array with 2 objects: fileName and json
        //for us just matter the first value
        String jsonFileName = fileName.split("\\.")[0];

        //because our getProperty recieves the name of the test data key value
        //we need to inform that to the class to get the correct file
        String propertyName;
        if(jsonFileName.equalsIgnoreCase("newPost")) {
            propertyName = "pathToPostFile";
        } else {
            propertyName = "pathToPatchFile";
        }

        String path = Configuration.getProperty(propertyName);

        extracted(fileName, path);
    }



    @When("a POST call is made to {string}")
    public void aPOSTCallIsMadeTo(String url) {
        //extract the response
                response = given()
                .header("Content-type", ContentType.JSON)
                .body(new Gson().toJson(requestBody))
                .log().all()
                .post(url)
                .then()
                .log().all()
                .extract().response();
    }


    @And("the response body contains an id")
    public void theResponseBodyContainsAnId() {

        String id = String.valueOf(Math.round((Double) response.as(Map.class).get("id")));
        logger.log(Level.INFO, "ID: "+id);

    }

    @And("the response contains {int} comments")
    public void theResponseContainsComments(int numberOfComments) {
        JsonArray commentsArray = response.as(JsonArray.class);
        int comments = commentsArray.size();
        assertEquals("Check if the response has " + numberOfComments + " comments."
                , numberOfComments, comments);

    }

    @When("a PATCH call is made to {string}")
    public void aPATCHCallIsMadeTo(String url) {
        response = given()
                .header("Content-type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(requestBody))
                .patch(url)
                .then()
                .extract().response();

        System.out.println(response.asPrettyString());
    }


    @And("the {string} equals {string}")
    public void theEquals(String key, String value) {
        //get the value according to the key and check if they are the same
        JsonObject responseObject = new Gson().fromJson(response.getBody().print(), JsonObject.class);
        assertEquals("The response must be the same as in feature file.", value, responseObject.get(key).getAsString());

    }

    /**
     * Method that extract the information from a json file and populates a JSONObject object
     * to be the request payload body no mather the request is.
     * @param fileName
     * @param path
     */
    private void extracted(String fileName, String path) {
        if(path.contains(fileName)) {

            try(FileReader reader = new FileReader("src/test/resources/"+ path)) {
                requestBody = new Gson().fromJson(reader, JsonObject.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
