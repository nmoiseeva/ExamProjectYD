package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GirlsProfilePage extends ParentPageWithHeaderMenu{

    @FindBy(xpath = "//div[@class='dte-l-pItemData']//*[contains(@class,'flowers ')]")
    private WebElement sendFlowersOrGiftButton;

    public GirlsProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/matches/1009469/";
    }

    public void checkIsGirlsProfileOpen(){
        checkUrlWithPattern();
        isElementDisplayed(sendFlowersOrGiftButton);
    }

    public void clickOnSendGiftButton(){
        checkIsGirlsProfileOpen();
        clickOnElement(sendFlowersOrGiftButton);
    }
}
