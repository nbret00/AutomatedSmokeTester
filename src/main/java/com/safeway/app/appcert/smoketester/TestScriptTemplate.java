/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safeway.app.appcert.smoketester;

/**
 *
 * @author nbret00
 * POJO for the information taken from the test Case
 * 
 */
public class TestScriptTemplate {
    
    private String logs = "";
    
    private String appCode = "";
    private String appURL = "";//tc1 access application URL
    private String appUserID = "";
    private String appPassword = "";
    private String homePageTitle = "";//tc2 validate homepage
    private String homePageElementType = ""; //tc2validate homepage
    private String homePageElement = "";//tc2validate homepage
    private String level1URL = "";//tc3 validate next page
    private String level1PageTitle = "";//tc3 validate next page
    private String level1ElementType = ""; //tc3
    private String Level1Element = "";//tc3 validate next page
    
    //holding the result message
    private String tc1Result_accessAppURL;
    
    private String tc2Result_validateHomePageTitle;
    private String tc2Result_validateHomeElement;
    
    private String tc3Result_validateNextPage_url;
    private String tc3Result_validateNextPage_title;
    private String tc3Result_validateNextPage_element;
    
    private String tc1_resultSummary = "";//login to the application
    private String tc2_resultSummary = "";//Inspect validity of home page by searching for the page title the element
    private String tc3_resultSummary = "";//Access next page of the application and validate title and selected element

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    public String getHomePageElementType() {
        return homePageElementType;
    }

    public void setHomePageElementType(String homePageElementType) {
        this.homePageElementType = homePageElementType;
    }

    public String getLevel1ElementType() {
        return level1ElementType;
    }

    public void setLevel1ElementType(String level1ElementType) {
        this.level1ElementType = level1ElementType;
    }

    public String getTc1_resultSummary() {
        return tc1_resultSummary;
    }

    public void setTc1_resultSummary(String tc1_resultSummary) {
        this.tc1_resultSummary = tc1_resultSummary;
    }

    public String getTc2_resultSummary() {
        return tc2_resultSummary;
    }

    public void setTc2_resultSummary(String tc2_resultSummary) {
        this.tc2_resultSummary = tc2_resultSummary;
    }

    public String getTc3_resultSummary() {
        return tc3_resultSummary;
    }

    public void setTc3_resultSummary(String tc3_resultSummary) {
        this.tc3_resultSummary = tc3_resultSummary;
    }
    

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppURL() {
        return appURL;
    }

    public void setAppURL(String appURL) {
        this.appURL = appURL;
    }

    public String getAppUserID() {
        return appUserID;
    }

    public void setAppUserID(String appUserID) {
        this.appUserID = appUserID;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }

    public String getHomePageTitle() {
        return homePageTitle;
    }

    public void setHomePageTitle(String homePageTitle) {
        this.homePageTitle = homePageTitle;
    }

    public String getHomePageElement() {
        return homePageElement;
    }

    public void setHomePageElement(String homePageElement) {
        this.homePageElement = homePageElement;
    }

    public String getLevel1URL() {
        return level1URL;
    }

    public void setLevel1URL(String level1URL) {
        this.level1URL = level1URL;
    }

    public String getLevel1PageTitle() {
        return level1PageTitle;
    }

    public void setLevel1PageTitle(String level1PageTitle) {
        this.level1PageTitle = level1PageTitle;
    }

    public String getLevel1Element() {
        return Level1Element;
    }

    public void setLevel1Element(String Level1Element) {
        this.Level1Element = Level1Element;
    }
    
    public String toString(){
        
        return "appcode:"+appCode +"-appUrl"+ appURL +"-AppUserID"+ appUserID +"-AppPass"+ appPassword+"-homeTitle"+homePageTitle+"-homePageElem"+homePageElement+"-Level2Url"+level1URL+"-Level2PageTitle"+level1PageTitle+"-Level2Elem"+Level1Element;
    }

    public String getTc1Result_accessAppURL() {
        return tc1Result_accessAppURL;
    }

    public void setTc1Result_accessAppURL(String tc1Result_accessAppURL) {
        this.tc1Result_accessAppURL = tc1Result_accessAppURL;
    }

    public String getTc2Result_validateHomePageTitle() {
        return tc2Result_validateHomePageTitle;
    }

    public void setTc2Result_validateHomePageTitle(String tc2Result_validateHomePage) {
        this.tc2Result_validateHomePageTitle = tc2Result_validateHomePage;
    }

    public String getTc2Result_validateHomeElement() {
        return tc2Result_validateHomeElement;
    }

    public void setTc2Result_validateHomeElement(String tc2Result_validateHomeElement) {
        this.tc2Result_validateHomeElement = tc2Result_validateHomeElement;
    }

    public String getTc3Result_validateNextPage_url() {
        return tc3Result_validateNextPage_url;
    }

    public void setTc3Result_validateNextPage_url(String tc3Result_validateNextPage_url) {
        this.tc3Result_validateNextPage_url = tc3Result_validateNextPage_url;
    }

    public String getTc3Result_validateNextPage_title() {
        return tc3Result_validateNextPage_title;
    }

    public void setTc3Result_validateNextPage_title(String tc3Result_validateNextPage_title) {
        this.tc3Result_validateNextPage_title = tc3Result_validateNextPage_title;
    }

    public String getTc3Result_validateNextPage_element() {
        return tc3Result_validateNextPage_element;
    }

    public void setTc3Result_validateNextPage_element(String tc3Result_validateNextPage_element) {
        this.tc3Result_validateNextPage_element = tc3Result_validateNextPage_element;
    }
    
    
}
