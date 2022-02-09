package pages;

import libs.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPageWithHeaderMenu {

    HomePage homePage = new HomePage(webDriver);

    @FindBy(xpath = "//input[@id = 'login_email']")
    private WebElement emailFieldPopUp;
    @FindBy(xpath = "//input[@id = 'login_password']")
    private WebElement passwordFieldPopUpMain;
    @FindBy(xpath = "//button[contains(text(), 'Sign In')]")
    private WebElement signInPopUpButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void successLogin() {
        homePage.openHomePage();
        openLoginPopUp();
        enterTextToElement(emailFieldPopUp, TestData.VALID_LOGIN);
        enterTextToElement(passwordFieldPopUpMain, TestData.VALID_PASS);
        clickOnElement(signInPopUpButton);
    }
}
