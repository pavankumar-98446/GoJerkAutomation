package com.operative.base.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.goJerk.BrowserUtils.WebDriverFactory;
import com.goJerk.configs.AutoConfigConstants;

import GoJerkAutomationPage.MidtransPillowPage;

public class BaseTest {

  private CustomeVerification customeVerification;
  SoftAssert softAssert;
  private WebDriver driver;
  private BasePage basePage;
  MidtransPillowPage midtransPillowPage;

  public SoftAssert getSoftAssert() {
    return softAssert;
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void launchBrowserApplication() {
    driver = new WebDriverFactory().createBrowser();
    BasePage.log("Launched " + AutoConfigConstants.browserType + " browser");
    BasePage.log("Environment : " + AutoConfigConstants.environmentUrl);
  }

  public BasePage getBasePage() {
    if (basePage == null) {
      basePage = new BasePage(getDriver());
    }
    return basePage;
  }

  public CustomeVerification getCustomeVerification() {
    if (customeVerification == null) {
      customeVerification = new CustomeVerification();
    }
    return customeVerification;
  }

  @BeforeMethod
  public void beforeMethod() {
    BasePage.step = 0;
    this.softAssert = new SoftAssert();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult itr) {
    final String testName = itr.getMethod().getMethodName();
    Reporter.log("=======================Test Results==============================", true);
    Reporter.log("Test : " + testName + " Test Status : " + itr.getStatus(), true);
    Reporter.log("=================================================================", true);
  }

  public MidtransPillowPage getMidtransPillowPage() {
    if (midtransPillowPage == null) {
      midtransPillowPage = new MidtransPillowPage(getDriver());
    }
    return midtransPillowPage;
  }
}
