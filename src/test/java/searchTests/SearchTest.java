package searchTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SearchTest extends BaseTest {

    @Test
    public void guestSearchingByAllCriteria(){
        searchPage.guestOpenSearchPage();
        searchPage.checkIsSearchPageOpen();
        searchPage.chooseOptionAtDropDownOnlineNow("Online now");
        searchPage.selectWebCamCheckbox();
        searchPage.fillAllInputAtSearchForm();
        searchPage.selectRandomValueAtAllDropDowns();
        searchPage.clickAtSearchButtonAndCheckResult();
    }
}
