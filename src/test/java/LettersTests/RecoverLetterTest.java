package LettersTests;

import baseTest.BaseTest;
import org.junit.Test;

public class RecoverLetterTest extends BaseTest {

    @Test
    public void userDeleteAndRecoverLetter() {
        lettersPage.openLettersPage();
        lettersPage.checkIsLettersPageOpen();
        lettersPage.openSearchLettersMenu();
        lettersPage.enterIdForSearch("234027");
        lettersPage.enterDateForFilter("2022-02-07");
        lettersPage.clickSearchButtonAtSearchMenu();
        lettersPage.checkIsAllLettersWasFound("234027");
        lettersPage.selectLetterForAction();
        lettersPage.clickOnDeleteLink();
        lettersPage.checkIsLetterWasDeleted("Letter was successfully deleted to bin.");
        lettersPage.selectLetterForAction();
        lettersPage.recoverLetterFromBin();
        lettersPage.checkIsLetterWasRecovered("Letter(s) was successfully recovered from bin.");
    }

}
