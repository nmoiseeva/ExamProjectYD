package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class ParentPageWithHeaderMenu extends ParentPage{

    @FindBy(xpath = "//span[@class='menu__link-text' and contains(text(),'Search')]")
    private WebElement searchLinkAtHeader;
    @FindBy(xpath = "//div[@class='header__top-menu']//a[contains(text(),'Sign In')]")
    private WebElement signInButtonMainPage;
    @FindBy(xpath = "//span[@class='menu__link-text' and contains(text(),'Ladies')]")
    private WebElement ladiesLinkAtHeader;


    public ParentPageWithHeaderMenu(WebDriver webDriver) {
        super(webDriver);
    }

    public void openSearchPage(){
        clickOnElement(searchLinkAtHeader);
    }

    public void openLoginPopUp(){
        clickOnElement(signInButtonMainPage);
    }

    public void openGalleryPage(){
        clickOnElement(ladiesLinkAtHeader);
    }

    public void checkIsGalleryLinkPresent(){
        isElementDisplayed(ladiesLinkAtHeader);
    }
}
