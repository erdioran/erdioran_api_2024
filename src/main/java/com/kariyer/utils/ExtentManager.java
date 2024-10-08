package com.kariyer.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.kariyer.base.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExtentManager {

    private static final ExtentReports EXTENT_REPORTS = new ExtentReports();

    static {
        String timeStamp = new SimpleDateFormat("dd_MMM_yyy_HH_mm_ss", Locale.ENGLISH).format(new Date());
        String reportFileName = "Test_Report_".concat(timeStamp).concat(".html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-reports/" + reportFileName);

        htmlReporter.config().setReportName("Kariyer BE Rest Assured Repors");
        htmlReporter.config().setDocumentTitle("Kariyer BE Rest Assured Repors");
        EXTENT_REPORTS.attachReporter(htmlReporter);
        EXTENT_REPORTS.setSystemInfo("Suite File", Constant.xmlSuiteFileName);
        EXTENT_REPORTS.setSystemInfo("Author", "Erdi Oran ");
        EXTENT_REPORTS.setSystemInfo("Environment", JsonManager.getData("url.base"));
    }

    private ExtentManager() {
    }

    public static ExtentReports getExtentReports() {
        return EXTENT_REPORTS;
    }
}