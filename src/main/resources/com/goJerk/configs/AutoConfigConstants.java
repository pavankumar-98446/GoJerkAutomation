package com.goJerk.configs;

public class AutoConfigConstants {

  public static String browserType = AutoConfiguration.initAutomatioProperties().getProperty("BrowserType");
  public static String environmentUrl = AutoConfiguration.initEnvironmentProperties().getProperty("EnvironmentUrl");
  public static String operatingSystemType = AutoConfiguration.initAutomatioProperties().getProperty("OperatingSystem");
  public static String firefox = "FIREFOX";
  public static String ie = "IE";
  public static String chrome = "Chrome";
  public static String firefoxWithProxy = "firefoxWithProxy";
  public static String window = "window";
  public static String pagetitle = "Sample Store";
}
