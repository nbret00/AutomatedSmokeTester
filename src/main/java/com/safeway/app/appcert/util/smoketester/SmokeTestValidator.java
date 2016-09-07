package com.safeway.app.appcert.util.smoketester;

import com.safeway.app.appcert.smoketester.TestScriptTemplate;
//import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nbret00
 */
public class SmokeTestValidator {

    private TestScriptTemplate tct;// = new TestScriptTemplate();

    private String successMsg = "Success";

    public SmokeTestValidator(TestScriptTemplate tct) {
        //initialize
        this.tct = tct;
    }

    public TestScriptTemplate getTestResult(TestScriptTemplate testexecution) {

        System.out.println("Start smoke test for application: " + testexecution.getAppCode());
        tct.setAppCode(testexecution.getAppCode());

        String logs = testexecution.getLogs();

        logs.concat(" Accessing application: " + validateAccessApp(testexecution.getAppURL()));
        logs.concat(" Home page title validation: " + validateHomePageTitle(testexecution.getHomePageTitle()));

        logs.concat(" Home page element validation: " + validateHomePageElement(testexecution.getHomePageElement()));

        logs.concat(" Next page URL validation: " + validateLevel1URL(testexecution.getLevel1URL()));
        logs.concat(" Next page title validation: " + validateLevel1Title(testexecution.getLevel1PageTitle()));
        logs.concat(" Next page element validation: " + validateLevel1Element(testexecution.getLevel1Element()));

        tct.setLogs(logs);
        System.out.println(logs);
        return tct;
    }

    private final String validateAccessAppErrMsg = "FAILED - Application ID does not have access.";

    public String validateAccessApp(String accessappURL) {

        //still in identity page after submit
        if (accessappURL.contains("identity")) {
            tct.setTc1_resultSummary("Failed");
            tct.setTc1Result_accessAppURL(validateAccessAppErrMsg);
            return validateAccessAppErrMsg;
        }

        //assumes we're in application URL since we're not in identity page
        tct.setTc1_resultSummary("Success");
        tct.setTc1Result_accessAppURL(successMsg);
        return successMsg;
    }

    private final String validateHomePageErrMsg = "FAILED - Home page title does not match.";

    public String validateHomePageTitle(String homepagetitle) {
        if (homepagetitle.compareToIgnoreCase(tct.getHomePageTitle()) == 0) {
            tct.setTc2Result_validateHomePageTitle(successMsg);

            tct.setTc2_resultSummary(successMsg);
            return successMsg;
        }
        tct.setTc2Result_validateHomePageTitle(validateHomePageErrMsg);

        tct.setTc2_resultSummary("Failed");
        return validateHomePageErrMsg;
    }

    private final String validateHomePageElementErrMsg = "FAILED - Element does not exist.";

    public String validateHomePageElement(String homepageelement) {

        if (homepageelement.compareToIgnoreCase(tct.getHomePageElement()) == 0) {
            if (tct.getHomePageElement().equals("")) {
                tct.setTc2Result_validateHomeElement("Skipped");
                return "Skipped";
            }
            tct.setTc2Result_validateHomeElement(successMsg);

            return successMsg;
        }
        tct.setTc2Result_validateHomeElement(validateHomePageElementErrMsg);
        return validateHomePageElementErrMsg;
    }

    private final String validateLevel2URLErrMsg = "FAILED - Page URL does not match to the expected URL.";
    public String validateLevel1URL(String level2URL) {
        if (tct.getLevel1URL().equals("")) {
            tct.setTc3Result_validateNextPage_url("Skipped");
            tct.setTc3_resultSummary("Skipped");
            return "Skipped";
        }
        if (level2URL.compareToIgnoreCase(tct.getLevel1URL()) == 0) {
            tct.setTc3Result_validateNextPage_url(successMsg);
            return successMsg;
        }
        tct.setTc3Result_validateNextPage_url(validateLevel2URLErrMsg);
        return validateLevel2URLErrMsg;
    }

    private final String validateLevel2TitleErrMsg = "FAILED - Page title does not match to the expected title.";

    public String validateLevel1Title(String level2title) {

        if (level2title.compareToIgnoreCase(tct.getLevel1PageTitle()) == 0) {
            if (tct.getLevel1PageTitle().equals("")) {
                tct.setTc3Result_validateNextPage_title("Skipped");
                tct.setTc3_resultSummary("Skipped");
                return "Skipped";
            }
            tct.setTc3Result_validateNextPage_title(successMsg);
            tct.setTc3_resultSummary(successMsg);
            return successMsg;
        }
        tct.setTc3_resultSummary("Failed");
        tct.setTc3Result_validateNextPage_title(validateLevel2TitleErrMsg);
        return validateLevel2TitleErrMsg;
    }

    private final String validateLevel2ElementErrMsg = "FAILED - Page Element does not exist.";

    public String validateLevel1Element(String level1Element) {
        System.out.println("validator log: " + level1Element + "-" + tct.getLevel1Element());
        if (level1Element.compareToIgnoreCase(tct.getLevel1Element()) == 0) {
            if (tct.getLevel1Element().equals("")) {
                //tct.setTc3_resultSummary("Skipped");
                return "Skipped";
            }
            tct.setTc3Result_validateNextPage_element(successMsg);
            //tct.setTc3_resultSummary("Success");
            return successMsg;
        }
        //tct.setTc3_resultSummary("Failed");
        tct.setTc3Result_validateNextPage_element(validateLevel2ElementErrMsg);
        return validateLevel2ElementErrMsg;
    }

}
