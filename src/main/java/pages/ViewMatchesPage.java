package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewMatchesPage extends ParentPage {

    @FindBy(xpath = "//*[@class='photo']")
    WebElement girlsPhotoCard;
    @FindBy(xpath = "//p[text()='No matches found']")
    WebElement noMatchMessage;

    public ViewMatchesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/matches/view/";
    }

    public void checkSearchResult() {
        checkUrlWithPattern();
        checkOneOfTwoElementsPresent(noMatchMessage, girlsPhotoCard);
    }

    public void clickOnGirlsCard() {
        clickOnElement(girlsPhotoCard);
    }
}