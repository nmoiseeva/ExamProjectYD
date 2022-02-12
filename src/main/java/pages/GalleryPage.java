package pages;

import org.junit.Assert;
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
        Assert.assertTrue(isElementDisplayed(girName));
    }

    public void chooseFavoriteCard() {
        clickOnElement(favoriteIconAtCard);
    }

    public void checkIsDeleteFromFavoriteIconPresent() {
        Assert.assertTrue(isElementDisplayed(deleteFromFavoritesIcon));
    }

    public void clickOnFavoriteTab() {
        clickOnElement(favoriteIconTab);
    }
}
