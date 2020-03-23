package com.operative.base.utils;

import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class CustomeVerification extends BaseTest {
  public void verifyEquals(SoftAssert softAssert, String actual, String expected) {
    softAssert.assertEquals(actual, expected, "Actual Value : " + actual + " Expected Value : " + expected + "\t\n");
    Reporter.log("Actual Value : " + actual + " Expected Value : " + expected);
  }
}
