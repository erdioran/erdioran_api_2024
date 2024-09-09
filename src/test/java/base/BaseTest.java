package base;

import com.aventstack.extentreports.ExtentTest;
import com.kariyer.utils.ExtentTestManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;


public abstract class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);


    @BeforeMethod(alwaysRun = true)
    public void startReport(Method method, ITestResult result, ITestContext context) {
        ThreadContext.put("testName", method.getName());
        LOGGER.info("Executing test method : [{}] in class [{}]", result.getMethod().getMethodName(),
                result.getTestClass().getName());
        String nodeName =
                StringUtils.isNotBlank(result.getMethod().getDescription()) ? result.getMethod().getDescription() : method.getName();
    //    ExtentTest node = ExtentTestManager.getTest().createNode(nodeName);
      //  ExtentTestManager.setNode(node);
        //ExtentTestManager.info("Test Started");
        String status = (String) context.getAttribute("previousTestStatus");
    }

    @AfterMethod(alwaysRun = true)
    public void finishTest(ITestResult result, ITestContext context) {
        if (!result.isSuccess()) {
            context.setAttribute("previousTestStatus", "failed");
        } else {
            context.setAttribute("previousTestStatus", "passed");
        }
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

    }

    @BeforeTest(alwaysRun = true)
    public void createApiHeaders() {

    }


}
