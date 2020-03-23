package com.DataProviders;

import java.io.File;

import org.testng.annotations.DataProvider;

import com.operative.base.utils.ExcelUtilities;

public class GoJerkDataProvider {
  @DataProvider(name = "verifyFlowForPurchasingPillow")
  public static Object[][] verifyFlowForPurchasingPillow() {
    final Object[][] objReturn = ExcelUtilities.getTableArray(
        System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "O1_Auto_TestData_GoJerk.xls",
        "GoJerk", "verifyFlowForPurchasingPillow");
    return objReturn;
  }
}