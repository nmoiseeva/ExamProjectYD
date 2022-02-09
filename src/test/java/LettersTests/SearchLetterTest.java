package LettersTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SearchLetterTest extends BaseTest {

    @Test
    public void userSearchLetterByParams() {
        lettersPage.openLettersPage();
        lettersPage.checkIsLettersPageOpen();
        lettersPage.openSearchLettersMenu();
        lettersPage.enterIdForSearch("1030819");
        lettersPage.enterDateForFilter("2022-01-01");
        lettersPage.chooseReadOption("Yes");
        lettersPage.clickSearchButtonAtSearchMenu();
        lettersPage.checkIsFilteredLetterCorrect();
    }
}
