package com.goJerk.BrowserUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.goJerk.configs.AutoConfigConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

  public WebDriver driver;

  @SuppressWarnings("deprecation")
  public WebDriver createBrowser() {
    if (AutoConfigConstants.browserType == null
        || AutoConfigConstants.browserType.equalsIgnoreCase(AutoConfigConstants.firefox)) {
      final String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator
          + "geckodriver.exe";
      System.setProperty("webdriver.gecko.driver", driverPath);
      System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
      System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
      final FirefoxProfile fp = new FirefoxProfile();
      fp.setPreference("security.mixed_content.block_active_content", false);
      fp.setPreference("security.mixed_content.block_display_content", true);
      fp.setPreference("browser.download.folderList", 2);
      fp.setPreference("browser.cache.disk.enable", false);
      fp.setPreference("browser.cache.memory.enable", false);
      fp.setPreference("browser.cache.offline.enable", false);
      fp.setPreference("network.http.use-cache", false);
      String dir = System.getProperty("user.dir");
      dir = dir + File.separator + "src" + File.separator + "resources" + File.separator + "downloadFiles";
      fp.setPreference("browser.download.dir", dir);
      fp.setPreference("browser.helperApps.neverAsk.saveToDisk",
          "image/jpg, text/csv,text/xml,application/xml,application/vnd.ms-excel,"
              + "application/x-excel,application/x-msexcel,application/excel,application/pdf,"
              + "text/html,application/octet-stream,application/x-ms-excel,application/x-excel,application/x-dos_ms_excel,application/xls,application/x-xls,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      fp.setPreference("webdriver.load.strategy", "fast");
      final DesiredCapabilities dc = DesiredCapabilities.firefox();
      dc.setCapability(FirefoxDriver.PROFILE, fp);
      dc.setCapability("marionette", true);
      driver = new FirefoxDriver(dc);
      driver.manage().deleteAllCookies();
      driver.manage().window().maximize();
    } else if (AutoConfigConstants.browserType.equalsIgnoreCase(AutoConfigConstants.chrome)
        && (AutoConfigConstants.operatingSystemType.equalsIgnoreCase(AutoConfigConstants.window))) {
      final ChromeOptions options = new ChromeOptions();
      options.addArguments("--disable-logging");
      options.addArguments("--disable-web-security");
      options.addArguments("--allow-running-insecure-content");
      options.addArguments("--disable-extensions");
      options.addArguments("test-type");
      options.addArguments("--start-maximized");
      options.addArguments("--disable-web-security");
      options.addArguments("--allow-running-insecure-content");
      options.addArguments("--start-maximized");
      options.addArguments("--lang=en-us");
      options.setPageLoadStrategy(PageLoadStrategy.NONE);
      options.addArguments("--disable-features=VizDisplayCompositor");
      options.setExperimentalOption("useAutomationExtension", false);
      final Map<String, Object> prefs = new HashMap<String, Object>();
      prefs.put("credentials_enable_service", false);
      prefs.put("password_manager_enabled", false);
      prefs.put("download.default_directory",
          System.getProperty("user.dir") + File.separator + "downloadFiles" + File.separator);
      options.setExperimentalOption("prefs", prefs);
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver(options);
    }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.get(AutoConfigConstants.environmentUrl);
    return driver;
  }

}
