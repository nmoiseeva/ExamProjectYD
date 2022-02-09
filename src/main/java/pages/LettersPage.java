package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LettersPage extends ParentPageWithHeaderMenu {

    LoginPage loginPage = new LoginPage(webDriver);
    MyAccountPage myAccountPage = new MyAccountPage(webDriver);

    @FindBy(css = "div.filter-icon-button")
    private WebElement searchButton;
    @FindBy(css = "input#c_id")
    private WebElement userIdInputField;
    @FindBy(xpath = "//input[@id='dt']")
    private WebElement dateToInputField;
    @FindBy(xpath = "//button[@class='filter-form-controls-btn-submit' and contains(text(),'Search')]")
    private WebElement searchButtonPopUp;
    @FindBy(xpath = "//select[@id='read']")
    private WebElement readSelect;
    @FindBy(xpath = "//div[@class='messages-list']//div[@class='detail']")
    private List<WebElement> listOfLetters;
    @FindBy(xpath = "//div[@id='item-586120443']")
    private WebElement expectedLetterFrom1030819;
    @FindBy(xpath = "//div[@class='filtered-by']//b[2]")
    private WebElement filteredByIdMessage;
    @FindBy(xpath = "//div[@class='subject' and contains(text(),\"I'm an unusual girl\")]")
    private WebElement expectedMessageFrom234027;
    @FindBy(xpath = "//div[@class='subject' and contains(text(),'unusual girl')]/../..//div[@class='jq-checkbox styled']")
    private WebElement checkboxFromLetter234027;
    @FindBy(xpath = "//a[text()='Delete']")
    private WebElement deleteLink;
    @FindBy(xpath = "//a[text()='Recover']")
    private WebElement recoverLink;
    @FindBy(xpath = "//a[text()='Recycle Bin']")
    private WebElement recycleBinFolderLink;
    @FindBy(xpath = "//a[text()='INBOX']")
    private WebElement inboxFolderLink;
    @FindBy(xpath = "//div[@class='info']")
    private WebElement infoBlock;

    public LettersPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/match/letters/";
    }

    public void openLettersPage() {
        loginPage.successLogin();
        myAccountPage.checkMyProfileIsOpen();
        myAccountPage.openLettersPage();
    }

    public void checkIsLettersPageOpen() {
        isElementDisplayed(searchButton);
        getRelativeUrl();
    }

    public void openSearchLettersMenu() {
        clickOnElement(searchButton);
    }

    public void enterIdForSearch(String idForSearch) {
        enterTextToElement(userIdInputField, idForSearch); //1030819
    }

    public void enterDateForFilter(String date) {
        enterTextToElement(dateToInputField, date);
    }

    public void chooseReadOption(String option) {
        selectTextInDropDown(readSelect, option);
    }

    public void clickSearchButtonAtSearchMenu() {
        clickOnElement(searchButtonPopUp);
    }

    public void checkIsFilteredLetterCorrect() {
        isElementDisplayed(expectedLetterFrom1030819);
        Assert.assertEquals("Number of letters is " + listOfLetters.size(), 1, listOfLetters.size());
    }

    public void checkIsAllLettersWasFound(String idForSearch) {
        checkTextContainAtElement(idForSearch, filteredByIdMessage);
        Assert.assertEquals("Number of letters is " + listOfLetters.size(), 2, listOfLetters.size());
    }

    public void selectLetterForAction() {
        clickOnElement(checkboxFromLetter234027);
    }

    public void clickOnDeleteLink() {
        clickOnElement(deleteLink);
        acceptAlert();
    }

    public void checkIsLetterWasDeleted(String text) {
        checkTextFromElement(text, infoBlock);
        Assert.assertEquals("Number of letters is " + listOfLetters.size(), 1, listOfLetters.size());
        clickOnElement(recycleBinFolderLink);
        isElementDisplayed(expectedMessageFrom234027);
    }

    public void recoverLetterFromBin() {
        clickOnElement(recoverLink);
        acceptAlert();
    }

    public void checkIsLetterWasRecovered(String text) {
        checkTextFromElement(text, infoBlock);
        clickOnElement(inboxFolderLink);
        openSearchLettersMenu();
        enterIdForSearch("234027");
        clickSearchButtonAtSearchMenu();
        isElementDisplayed(expectedMessageFrom234027);
    }
}
