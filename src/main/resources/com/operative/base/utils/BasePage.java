package com.operative.base.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.goJerk.BrowserUtils.WebDriverFactory;

public class BasePage extends WebDriverFactory {

  static int step = 0;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public WebDriver getWebdriver() {
    return driver;
  }

  public Actions getActions() {
    return new Actions(this.driver);
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void clear(By locator) {
    driver.findElement(locator).clear();
  }

  public void type(By locator, String value) {
    driver.findElement(locator).sendKeys(value);
  }

  public String getText(By locator) {
    return driver.findElement(locator).getText();
  }

  public String getAttribute(By locator, String Attribute) {
    return driver.findElement(locator).getAttribute(Attribute);
  }

  public List<WebElement> getElements(By locator) {
    return driver.findElements(locator);
  }

  public void moveMouseHere(By locator) {
    final Actions act = new Actions(driver);
    act.moveToElement(driver.findElement(locator)).build().perform();

  }

  public void safeClick(By locator) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.elementToBeClickable(locator));
      driver.findElement(locator).click();
    } catch (final WebDriverException e) {
      final Actions action = new Actions(driver);
      action.moveToElement(driver.findElement(locator)).perform();
      action.click().perform();
    }
  }

  public void safeType(By locator, String value) {
    final WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(value);
  }

  public void safeClear(By locator) {
    final WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.elementToBeClickable(locator));
    driver.findElement(locator).clear();
  }

  public void safeCheck(By locator) {
    if (driver.findElement(locator).getAttribute("checked") == null) {
      driver.findElement(locator).click();
    }
  }

  public void safeUnCheck(By locator) {
    if (driver.findElement(locator).getAttribute("checked") == null) {
    } else {
      driver.findElement(locator).click();
    }
  }

  public void doubleClick(WebElement web) {
    getActions().doubleClick(web).build().perform();
    log(" Double Clicked on Element");

  }

  public void doubleClick(By locator) {
    getActions().doubleClick(driver.findElement(locator)).build().perform();
    log(" Double Clicked on Element");

  }

  public void clickElementWithJavaScript(By locator) {
    final JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", driver.findElement(locator));
  }

  public String getElementValueUsingJavaScript(String script) {
    final JavascriptExecutor executor = (JavascriptExecutor) driver;
    final String elementValue = executor.executeScript(script).toString();
    return elementValue;
  }

  public void doubleClickElementUsingJavaScript(By locator) {
    final WebElement web = driver.findElement(locator);
    ((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
        + "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
        + "arguments[0].dispatchEvent(evt);", web);
  }

  public void doubleClickElementUsingJavaScript(WebElement web) {
    ((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
        + "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
        + "arguments[0].dispatchEvent(evt);", web);
  }

  public static void log(String message) {
    Reporter.log("Step:" + step + "-> " + message, true);
    step++;
  }

  public void waitForPage(int sec) {
    final JavascriptExecutor je = (JavascriptExecutor) driver;
    final int waitTime = sec * 1000;
    int counter = 0;
    counter = 0;
    Long ajaxCount = (long) -1;
    do {
      try {
        Thread.sleep(1000);
      } catch (final InterruptedException e1) {
        e1.printStackTrace();
      }
      counter += 1000;
      try {
        ajaxCount = (Long) ((je)).executeScript("return window.dwr.engine._batchesLength");
      } catch (final WebDriverException e) {

      }
    } while (ajaxCount.intValue() > 0 && counter < waitTime);
  }

  public boolean isElementPresent(By by) {
    boolean value;
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    try {
      driver.findElement(by).isDisplayed();
      value = true;
    } catch (final NoSuchElementException e) {
      value = false;
    } finally {
      driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    return value;
  }

  public boolean isDisplayed(By locater) {
    final boolean i = getWebdriver().findElement(locater).isDisplayed();
    log("Locater is Displayed");
    return i;
  }

  public void waitForElementPresence(By locator, int sec) {
    waitForPage(60);
    final WebDriverWait wait = new WebDriverWait(driver, sec);
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  public void switchToFrame(By by) {
    driver.switchTo().frame(driver.findElement(by));
    log("switched to frame");
  }

  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
    log("Switched to default content");
  }

  public void threadSleep(int ms) {
    try {
      Thread.sleep(ms);
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

}