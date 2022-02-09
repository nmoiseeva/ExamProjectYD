package giftsTests;

import baseTest.BaseTest;
import org.junit.Test;

public class GiftsTest extends BaseTest {

    String giftWasNotSentMessage =
            "You cannot proceed because you do not have sufficient amount of credits on your virtual account at YesDates.com. ";


    @Test
    public void userSendGiftNotEnoughMoney() {
        giftsPage.openGiftsPage();
        giftsPage.checkIsGiftsPageOpen();
        giftsPage.chooseGiftTab();
        giftsPage.chooseGift();
        giftsPage.checkGiftErrorMessage(giftWasNotSentMessage);
    }
}
