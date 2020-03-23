package GoJerkAutomationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.operative.base.utils.BasePage;

public class MidtransPillowPage extends BasePage {

  public MidtransPillowPage(WebDriver driver) {
    super(driver);
  }

  public By buttonForBuy = By.xpath("//a[@class='btn buy' and text()='BUY NOW']");
  public By cartCheckOut = By.xpath("//div[@class='cart-checkout' and text()='CHECKOUT']");
  public By orderSummaryPopup = By.cssSelector("div#application");
  public By summaryFrame = By.id("snap-midtrans");
  public By continueBtn = By.xpath("//span[text()='Continue']");
  public By creditCardPayment = By.xpath("//div[text()='Credit Card']//parent::div");
  public By cardNumber = By.name("cardnumber");
  public By expiryDate = By.xpath("//input[@placeholder='MM / YY']");
  public By cvv = By.xpath("//label[text()='CVV']//parent::div/input");
  public By payNow = By.xpath("//span[text()='Pay Now']//parent::div");
  public By password = By.xpath("//input[@type='password']");
  public By okBtn = By.name("ok");
  public By paymentIfram = By.xpath("//div[@id='application']//iframe");
  public By transactionStatus = By.xpath("//div[@class='card-container full']//*[contains(text(),'Transaction')]");

  public MidtransPillowPage clickButtonForBuy() {
    safeClick(buttonForBuy);
    log("Clicked Buy now button ");
    return this;
  }

  /**
   * TODO: DEFINE ME! pavankumar This Method for Click cart Check Out button and SwichTo summaryFrame
   *
   * @return
   */
  public MidtransPillowPage clickCartCheckOut() {
    safeClick(cartCheckOut);
    log("Clicked cart Check Out button ");
    switchToFrame(summaryFrame);
    waitForElementPresence(orderSummaryPopup, 30);
    log("OrderSummaryPopup identified");
    return this;
  }

  /**
   * TODO: DEFINE ME! pavankumar
   * 
   * @return
   */
  public MidtransPillowPage clickContinueBtn() {
    safeClick(continueBtn);
    log("Clicked continue button ");
    return this;
  }

  /**
   * TODO: DEFINE ME! pavankumar
   * 
   * @return
   */
  public MidtransPillowPage clickCreditCardPaymentBtn() {
    safeClick(creditCardPayment);
    log("Clicked creditcard payment button ");
    return this;
  }

  /**
   * TODO: DEFINE ME! pavankumar
   *
   * @param cardNum
   * @param exDate
   * @param cvvNum
   *          This method will work for entering required credit card details
   * @return
   */
  public MidtransPillowPage enterCreditCardDetails(String cardNum, String exDate, String cvvNum) {
    safeType(cardNumber, cardNum);
    log("Entered Card Number as : " + cardNum);
    safeType(expiryDate, exDate);
    log("Entered expiry Date as : " + exDate);
    safeType(cvv, cvvNum);
    log("Entered Card Number as : " + cvvNum);
    return this;
  }

  /**
   * TODO: DEFINE ME! pavankumar For clicking PayNow Button
   *
   * @return
   */
  public MidtransPillowPage clickPayNowBtn() {
    safeClick(payNow);
    log("Clicked payNow button ");
    return this;
  }

  /**
   * TODO: DEFINE ME! pavankumar
   *
   * @param otp
   * @return
   */
  public MidtransPillowPage enterPasswordforPayment(String otp) {
    threadSleep(10000);
    switchToFrame(paymentIfram);
    safeType(password, otp);
    log("Enetred OTP as :" + otp);
    return this;
  }

  /**
   * TODO: click Ok Button method
   *
   * @return
   */
  public MidtransPillowPage clickOkBtn() {
    safeClick(okBtn);
    log("Clicked OK button ");
    driver.switchTo().parentFrame();
    waitForElementPresence(transactionStatus, 5);
    return this;
  }

  public String getTransactionStatus() {
    return driver.findElement(transactionStatus).getText();

  }
}
