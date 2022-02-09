package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GalleryPage extends ParentPageWithHeaderMenu {

    @FindBy(xpath = "//div[@class='name']/a")
    private WebElement girName;
    @FindBy(xpath = "//span[text()='Favorites']/..")
    private WebElement favoriteIconTab;
    @FindBy(xpath = "//span[@class='icon-favorite']")
    private WebElement favoriteIconAtCard;
    @FindBy(xpath = "//div[@class='favorite active']")
    private WebElement deleteFromFavoritesIcon;


    public GalleryPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/matches/ladies/";
    }

    public void checkIsGalleryPageOpen() {
        checkUrl();
        isElementDisplayed(girName);
    }

    public void chooseFavoriteCard() {
        clickOnElement(favoriteIconAtCard);
    }

    public void checkIsDeleteFromFavoriteIconPresent() {
        isElementDisplayed(deleteFromFavoritesIcon);
    }

    public void clickOnFavoriteTab() {
        clickOnElement(favoriteIconTab);
    }
}
