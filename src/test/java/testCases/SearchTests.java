package testCases;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.kariyer.utils.ExtentTestManager;
import com.kariyer.utils.JsonManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import java.util.HashMap;
import java.util.Map;

import static com.kariyer.utils.JsonManager.getModifiedJsonBody;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class SearchTests extends BaseTest {

    public static final Logger LOGGER = LogManager.getLogger(SearchTests.class);
    Response response;
    String logResponse;


    @BeforeMethod()
    public void beforeSearchTest(ITestContext context) {
        ExtentTest test = ExtentTestManager.getNode();
        test.assignCategory("Search Tests");

    }


    @Test(description = "Search Case", priority = 1)
    public void seachCase() {

        ExtentTest test = ExtentTestManager.getNode();
        test.info("Search Case 1");
        ExtentTestManager.info("Search Case 1");

        Map<String, Object> changes = new HashMap<>();

        changes.put("memberId", 35042722);
        changes.put("currentPage", 1);
        changes.put("size", 50);
        changes.put("keyword", "QA");
        changes.put("dontShowAppliedJobs", true);

        JSONArray languageArray = new JSONArray();
        languageArray.add("1");
        changes.put("language", languageArray);

        JSONObject jsonBody = getModifiedJsonBody("search.json", changes);
        LOGGER.info("Request body: " +jsonBody);

        RequestSpecification request = given().baseUri(JsonManager.getData("url.base"))
                .and().body(jsonBody).and().header("Content-Type", "application/json");
        response = request.when().post(JsonManager.getData("url.search"));
        response.then().log().all();

        response.then().body("data.jobs.items", notNullValue());

    }


}
