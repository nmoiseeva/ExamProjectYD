package FavoritesTests;

import baseTest.BaseTest;
import org.junit.Test;

public class FavoritesTest extends BaseTest {

    @Test
    public void addAndDeleteFavoritesCard() {
        favoritePage.checkIsFavoritesPageEmpty();
        favoritePage.addFavoriteCardFromGallery();
        favoritePage.openFavoritePageAfterAdding();
        favoritePage.checkIsFavoritePageOpen();
        favoritePage.checkIsCardWasAdded();
    }
}
