package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FavoritePage extends ParentPageWithHeaderMenu {

    GalleryPage galleryPage = new GalleryPage(webDriver);

    @FindBy(xpath = "//div[@class='dte-l-catalogItem']")
    private List<WebElement> galleryCards;
    @FindBy(xpath = "//div[@class='dte-l-catalogSubstance']")
    private WebElement noCardsInfoBlock;
    @FindBy(xpath = "//div[@class='favorite active']")
    private WebElement deleteFromFavoritesIcon;

    private String noFavoritesMessage = "Your favorites list is empty.";

    public FavoritePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/match/favorites/";
    }

    public void checkIsFavoritePageOpen() {
        Assert.assertTrue(webDriver.getTitle().contains("Favorites"));
        checkUrl();
        checkIsGalleryLinkPresent();
    }

    public void checkIsFavoritesPageEmpty() {
        LoginPage loginPage = new LoginPage(webDriver);
        MyAccountPage myAccountPage = new MyAccountPage(webDriver);
        loginPage.successLogin();
        myAccountPage.checkMyProfileIsOpen();
        myAccountPage.openFavoritesPage();
        checkIsFavoritePageOpen();
        deleteAllCards();
    }

    public void deleteAllCards() {
        if (isElementDisplayed(deleteFromFavoritesIcon)) {
            while (!galleryCards.isEmpty()) {
                clickOnElement(deleteFromFavoritesIcon);
                webDriver.navigate().refresh();
                logger.info("All cards were deleted");
            }
        } else if (isElementDisplayed(noCardsInfoBlock)) {
            checkTextFromElement(noFavoritesMessage, noCardsInfoBlock);
            logger.info("There were no cards at page");
        }
    }

    public void addFavoriteCardFromGallery() {
        openGalleryPage();
        galleryPage.checkIsGalleryPageOpen();
        galleryPage.chooseFavoriteCard();
        galleryPage.checkIsDeleteFromFavoriteIconPresent();
    }

    public void openFavoritePageAfterAdding() {
        galleryPage.clickOnFavoriteTab();
    }

    public void checkIsCardWasAdded() {
        Assert.assertEquals("Numbers of cards are " + galleryCards.size(), 1, galleryCards.size());
        clickOnElement(deleteFromFavoritesIcon);
        webDriver.navigate().refresh();
        checkTextFromElement(noFavoritesMessage, noCardsInfoBlock);
    }
}
