package com.safeway.app.appcert.smoketester;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.safeway.app.appcert.util.smoketester.*;
/**
 *
 * @author nbret00
 */
public class SmokeTester {

    @Test
    public void executeSmokeTest() throws Exception {
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        
        System.setProperty("webdriver.chrome.driver", "C:\\Nino\\ChromeWebDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        TestCaseReader tcreader = new TestCaseReader();
        List<TestScriptTemplate> tcl = tcreader.readExcel();

        List<TestScriptTemplate> validatedTestScript = new ArrayList();
        
        String log_execution = "";
        Iterator<TestScriptTemplate> i = tcl.iterator();
        while (i.hasNext()) {
            TestScriptTemplate testscript = i.next();
            //collect the results
            TestScriptTemplate testexecution = new TestScriptTemplate();

            testexecution.setAppCode(testscript.getAppCode());
            log_execution = log_execution + " Start smoke testing for application code: " + testexecution.getAppCode();

            //access the URL
            driver.get(testscript.getAppURL());

            //login if not yet
            if (driver.getCurrentUrl().contains("identity.safeway.com")) {
                //key in userid and password
                WebElement weusername = driver.findElement(By.id("username"));
                //System.out.println("tag:" + weusername.getTagName());
                weusername.sendKeys(testscript.getAppUserID());

                WebElement wepassword = driver.findElement(By.id("password"));
                //System.out.println("tag:" + wepassword.getTagName());
                wepassword.sendKeys(testscript.getAppPassword());

                WebElement weloginform = driver.findElement(By.name("loginData"));
                //System.out.println("tag:" + weloginform.getTagName());
                weloginform.submit();
            }

            //recoding URL; required so no need to check for nullity
            testexecution.setAppURL(driver.getCurrentUrl());
            log_execution = log_execution + " Current URL: "+driver.getCurrentUrl();

            //recoding title; required so no need to check for nullity
            testexecution.setHomePageTitle(driver.getTitle());
            log_execution = log_execution + " Login Successful";
            log_execution = log_execution + " Page Title: "+driver.getTitle();

            if (isElementExist(testscript.getHomePageElementType(), testscript.getHomePageElement(), driver)) {
                System.out.println("Element match!" + testscript.getHomePageElement());
                log_execution = log_execution + " Home Page Element validation...";
                testexecution.setHomePageElement(testscript.getHomePageElement());
            } else {
                testexecution.setHomePageElement("not found");
            }

            //next page validation
            if (!testscript.getLevel1URL().isEmpty() || !testscript.getLevel1URL().equals("")) {
                //go to next level page
                driver.get(testscript.getLevel1URL());
                log_execution = log_execution + " Next Page validation URL: "+testscript.getLevel1URL();

                testexecution.setLevel1URL(driver.getCurrentUrl());
                System.out.println("execution log: current level 1 URL on set" + testexecution.getLevel1URL());

                if (!testscript.getLevel1PageTitle().isEmpty() || !testscript.getLevel1PageTitle().equals("")) {
                    testexecution.setLevel1PageTitle(driver.getTitle());
                    log_execution = log_execution + " Next Page title validation: "+driver.getTitle();
                }

                if (isElementExist(testscript.getLevel1ElementType(), testscript.getLevel1Element(), driver)) {
                    testexecution.setLevel1Element(testscript.getLevel1Element());
                    log_execution = log_execution + " Next Page element validation: "+testscript.getLevel1Element();
                } else {
                    testexecution.setLevel1Element("not found");
                }

            }
            testexecution.setLogs(log_execution);
            System.out.println("Execution Log: "+log_execution);
            log_execution = "";
            SmokeTestValidator testvalidator = new SmokeTestValidator(testscript);
            TestScriptTemplate testingresult = testvalidator.getTestResult(testexecution);
            validatedTestScript.add(testingresult);

        }

        tcreader.writetoExcel(validatedTestScript);
        //Close the browser
        driver.quit();
        //return log_execution;
    }

    private boolean isElementExist(String elementyype, String elementtosearch, WebDriver driver) {
        System.out.println("input for isElementExist: "+ elementyype +"-"+ elementtosearch);
        if (elementyype.compareToIgnoreCase("class") == 0) {
            try {
                driver.findElement(By.className(elementtosearch));
                return true;
            } catch (org.openqa.selenium.NoSuchElementException nse) {
                return false;
            }
        }
        if (elementyype.compareToIgnoreCase("xpath") == 0) {
            try {
                driver.findElement(By.xpath(elementtosearch));
                return true;
            } catch (org.openqa.selenium.NoSuchElementException nse) {
                return false;
            }
        }

        if (elementyype.compareToIgnoreCase("id") == 0) {
            try {
                driver.findElement(By.id(elementtosearch));
                return true;
            } catch (org.openqa.selenium.NoSuchElementException nse) {
                return false;
            }
        }

        return true;
    }
    
    

}
