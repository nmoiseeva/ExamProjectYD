package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftsPage extends ParentPageWithHeaderMenu {

    LoginPage loginPage = new LoginPage(webDriver);
    MyAccountPage myAccountPage = new MyAccountPage(webDriver);
    GirlsProfilePage girlsProfilePage = new GirlsProfilePage(webDriver);
    SearchPage searchPage = new SearchPage(webDriver);
    ViewMatchesPage viewMatchesPage = new ViewMatchesPage(webDriver);

    @FindBy(xpath = "//a[contains(text(),'Tasty Gifts')]")
    private WebElement tastyGiftTab;
    @FindBy(xpath = "//div[contains(text(), 'Coffee Set')]/..//button")
    private WebElement coffeeSendGiftButton;
    @FindBy(css = "button.btn1")
    private WebElement addCreditButton;
    @FindBy(xpath = "//div[@class='credits-low-block']")
    private WebElement notEnoughCreditsPopUp;

    public GiftsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/matches/1009469/send-gift/";
    }

    public void openGiftsPage() {
        loginPage.successLogin();
        myAccountPage.checkMyProfileIsOpen();
        openSearchPage();
        searchPage.checkIsSearchPageOpen();
        searchPage.findGirlById();
        viewMatchesPage.checkSearchResult();
        viewMatchesPage.clickOnGirlsCard();
        girlsProfilePage.checkIsGirlsProfileOpen();
        girlsProfilePage.clickOnSendGiftButton();
    }

    public void checkIsGiftsPageOpen() {
        checkUrlWithPattern();
        isElementDisplayed(tastyGiftTab);
    }

    public void chooseGiftTab() {
        clickOnElement(tastyGiftTab);
    }

    public void chooseGift() {
        clickOnElement(coffeeSendGiftButton);
    }

    public void checkGiftErrorMessage(String text) {
        checkTextContainAtElement(text, notEnoughCreditsPopUp);
        isElementDisplayed(addCreditButton);
    }

}
