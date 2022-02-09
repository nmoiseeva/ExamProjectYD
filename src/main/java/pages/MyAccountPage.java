package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends ParentPageWithHeaderMenu {

    @FindBy(xpath = "//*[@class='fancybox-image']")
    private WebElement imageUsers;
    @FindBy(xpath = "//div[@class='dte-l-pItemMenuBlock user-menu']//a[@class='message']")
    private WebElement myLettersLink;
    @FindBy(xpath = "//div[@class='dte-l-pItemMenuBlock user-menu']//a[@class='favourites']")
    private WebElement favoritesLink;

    public MyAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/match/";
    }

    public void checkMyProfileIsOpen() {
        closeCookies();
        isElementDisplayed(imageUsers);
        checkUrl();
    }

    public void openLettersPage() {
        clickOnElement(myLettersLink);
    }

    public void openFavoritesPage() {
        clickOnElement(favoritesLink);
    }
}
