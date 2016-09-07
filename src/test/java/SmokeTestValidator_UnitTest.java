
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
public class SmokeTestValidator_UnitTest {
    
    private TestScriptTemplate tct;// = new TestScriptTemplate();
    
    private String successMsg = "Success";
    
    public SmokeTestValidator_UnitTest(TestScriptTemplate tct){
        //initialize
        this.tct = tct;
    }
    
    
    public TestScriptTemplate getTestResult(TestScriptTemplate testexecution){
        
        System.out.println("Start smoke test for application: "+testexecution.getAppCode());
        tct.setAppCode(testexecution.getAppCode());
        
        String logs = testexecution.getLogs();
        
        //System.out.println("Accessing application: "+ validateAccessApp(testexecution.getAppURL()));
        logs.concat(" Home page title validation: "+ validateHomePageTitle(testexecution.getHomePageTitle()));
        
        logs.concat(" Home page element validation: "+ validateHomePageElement(testexecution.getHomePageElement()));
        
        logs.concat(" Next page URL validation: "+ validateLevel2URL(testexecution.getLevel1URL()));
        logs.concat(" Next page title validation: "+ validateLevel2Title(testexecution.getLevel1PageTitle()));
        logs.concat(" Next page element validation: "+ validateLevel2Element(testexecution.getLevel1Element()));
    
        tct.setLogs(logs);
        
        return tct;
    }
    
    private final String validateHomePageErrMsg = "FAILED - Home page title does not match.";
    public String validateHomePageTitle(String homepagetitle){
        if (homepagetitle.compareToIgnoreCase(tct.getHomePageTitle()) == 0){
            tct.setTc2Result_validateHomePageTitle(successMsg);
            tct.setTc1_resultSummary(successMsg);
            tct.setTc2_resultSummary(successMsg);            
            return successMsg;
        }
        tct.setTc2Result_validateHomePageTitle(validateHomePageErrMsg);
        tct.setTc1_resultSummary("Failed");
        tct.setTc2_resultSummary("Failed");
        return validateHomePageErrMsg;
    }
    
    private final String validateHomePageElementErrMsg = "FAILED - Element does not exist.";
    public String validateHomePageElement(String homepageelement){
        
        if (homepageelement.compareToIgnoreCase(tct.getHomePageElement()) == 0){
            if (tct.getHomePageElement().equals("")){
                return "Skipped";
            }
            tct.setTc2Result_validateHomeElement(successMsg);
            
            return successMsg;
        }
        tct.setTc2Result_validateHomeElement(validateHomePageElementErrMsg);
        return validateHomePageElementErrMsg;
    }    
    
    private final String validateAccessAppErrMsg = "FAILED - Application URL does not match to the expected URL.";
    public String validateAccessApp(String accessappURL){
        
        if (accessappURL.compareToIgnoreCase(tct.getAppURL()) == 0){
            tct.setTc1Result_accessAppURL(successMsg);
            return successMsg;
        }
        tct.setTc2Result_validateHomeElement(validateAccessAppErrMsg);
        return validateAccessAppErrMsg;
    }        
    
    private final String validateLevel2URLErrMsg = "FAILED - Page URL does not match to the expected URL.";
    public String validateLevel2URL(String level2URL){
        
        if (level2URL.compareToIgnoreCase(tct.getLevel1URL()) == 0){
            tct.setTc3Result_validateNextPage_url(successMsg);
            return successMsg;
        }
        tct.setTc3Result_validateNextPage_url(validateLevel2URLErrMsg);
        return validateLevel2URLErrMsg;
    }      
    
    private final String validateLevel2TitleErrMsg = "FAILED - Page title does not match to the expected title.";
    public String validateLevel2Title(String level2title){
        
        if (level2title.compareToIgnoreCase(tct.getLevel1PageTitle()) == 0){
            tct.setTc3Result_validateNextPage_title(successMsg);
            tct.setTc1_resultSummary(successMsg);
            return successMsg;
        }
        tct.setTc1_resultSummary("Failed");
        tct.setTc3Result_validateNextPage_title(validateLevel2TitleErrMsg);
        return validateLevel2TitleErrMsg;
    }    
    
    private final String validateLevel2ElementErrMsg = "FAILED - Page Element does not exist.";
    public String validateLevel2Element(String level1Element){
        System.out.println("validator log: "+level1Element+"-"+tct.getLevel1Element());
        if (level1Element.compareToIgnoreCase(tct.getLevel1Element()) == 0){
            if (tct.getLevel1Element().equals("")){
                tct.setTc3_resultSummary("Skipped");
                return "Skipped";
            }            
            tct.setTc3Result_validateNextPage_element(successMsg);
            tct.setTc3_resultSummary("Success");
            return successMsg;
        }
        tct.setTc3_resultSummary("Failed");
        tct.setTc3Result_validateNextPage_element(validateLevel2ElementErrMsg);
        return validateLevel2ElementErrMsg;
    }        
    
}
