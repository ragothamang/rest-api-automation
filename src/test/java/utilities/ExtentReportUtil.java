package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    // Initialize the ExtentReports and the SparkReporter
    public static synchronized void initialize() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/test-report.html");
            sparkReporter.config().setReportName("API Automation Test Suite");
            sparkReporter.config().setDocumentTitle("Extent Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Your Name");
        }
    }

    // Start logging a test case
    public static synchronized void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        testThread.set(extentTest);
    }

    // Get the current thread's ExtentTest instance
    public static synchronized ExtentTest getTest() {
        return testThread.get();
    }

    // Log test information
    public static synchronized void logTestInfo(String message) {
        getTest().info(message);
    }

    // Log test pass
    public static synchronized void logTestPass(String message) {
        getTest().pass(message);
    }

    // Log test failure
    public static synchronized void logTestFail(String message) {
        getTest().fail(message);
    }

    // End the test case
    public static synchronized void endTest() {
        testThread.remove(); // Clean up ThreadLocal variable
    }

    // Flush the ExtentReports instance
    public static synchronized void close() {
        if (extent != null) {
            extent.flush();
        }
    }
}

