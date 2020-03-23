package com.GoJerk.MidtransPillow;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.DataProviders.GoJerkDataProvider;
import com.goJerk.configs.AutoConfigConstants;
import com.operative.base.utils.BasePage;
import com.operative.base.utils.BaseTest;

/**
 * TODO: DEFINE ME!! pavankumar
 *
 * @author pavankumar
 * @date Mar 21, 2020
 * @description
 *
 *              1. Create an automation test script to test end to end checkout flow for purchasing “Pillow”
 *              using Credit Card as payment method. This should be a SUCCESSFUL payment flow.
 *
 *              2. Create the second script to test end to end checkout flow for purchasing “Pillow” using
 *              Credit Card as payment method. This should be FAILED payment flow.
 *
 */

public class VerifyFlowForPurchasingPillowUsingCreditCardAsPaymentMethod extends BaseTest {

  @BeforeTest
  public void launchApplication() {
    // Application with launched with respective browser declared in properties file
    launchBrowserApplication();
  }

  @AfterTest
  public void closeBrowser() {
    // close the browser after executed test
    getDriver().quit();
  }

  @Test(dataProviderClass = GoJerkDataProvider.class, dataProvider = "verifyFlowForPurchasingPillow", alwaysRun = true)
  public void verifyFlowForPurchasingPillow(String paymentType, String cardNumber, String expiryDate, String cvv,
      String bankOTP, String expectedMessage) {
    getDriver().navigate().to(AutoConfigConstants.environmentUrl);
    // verify Title of the page to ensure we are in right path
    getSoftAssert().assertEquals(getDriver().getTitle(), AutoConfigConstants.pagetitle);
    // verifying what type of payment scenario executing
    BasePage.log(paymentType);
    // click BuyNow button
    getMidtransPillowPage().clickButtonForBuy().
    // click CartCheckout Button and continue with CreditCard Payment
        clickCartCheckOut().clickContinueBtn().clickCreditCardPaymentBtn().
        // enter credit card details
        enterCreditCardDetails(cardNumber, expiryDate, cvv).
        // click PayNow button for payment
        clickPayNowBtn().
        // enter OTP and click ok Button
        enterPasswordforPayment(bankOTP).clickOkBtn();
    // verify Transaction Status with expected output
    getSoftAssert().assertEquals(getMidtransPillowPage().getTransactionStatus(), expectedMessage);
    // call assertions
    getSoftAssert().assertAll();
  }

}
