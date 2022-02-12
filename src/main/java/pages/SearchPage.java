package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends ParentPageWithHeaderMenu {

    @FindBy(xpath = "//div[@class='jq-selectbox__trigger']")
    private WebElement onlineNowDropDownMenu;
    @FindBy(id = "camera_status-styler")
    private WebElement hasWebcamCheckBox;
    @FindBy(xpath = "//*[@name='filter[age_from]']")
    private WebElement ageFromFilter;
    @FindBy(xpath = "//*[@name='filter[age_to]']")
    private WebElement ageToFilter;
    @FindBy(xpath = "//*[@name='filter[height_from]']")
    private WebElement heightFromFilter;
    @FindBy(xpath = "//*[@name='filter[height_to]']")
    private WebElement heightToFilter;
    @FindBy(xpath = "//*[@name='filter[weight_from]']")
    private WebElement weightFromFilter;
    @FindBy(xpath = "//*[@name='filter[weight_to]']")
    private WebElement weightToFilter;
    @FindBy(xpath = "//*[@name='filter[hair_color]']")
    private WebElement hairColorDropDown;
    @FindBy(xpath = "//*[@name='filter[eyes_color]']")
    private WebElement eyeColorDropDown;
    @FindBy(xpath = "//*[@name='filter[ethnicity]']")
    private WebElement ethnicityDropDown;
    @FindBy(xpath = "//*[@name='filter[drink]']")
    private WebElement drinkDropDown;
    @FindBy(xpath = "//*[@name='filter[smoke]']")
    private WebElement smokeDropDown;
    @FindBy(xpath = "//*[@name='filter[religion]']")
    private WebElement religionDropDown;
    @FindBy(xpath = "//*[@name='filter[marital_status]']")
    private WebElement maritalStatusDropDown;
    @FindBy(xpath = "//*[@name='filter[children]']")
    private WebElement childrenDropDown;
    @FindBy(xpath = "//*[@name='filter[education]']")
    private WebElement educationDropDown;
    @FindBy(xpath = "//*[@name='filter[country]']")
    private WebElement countryDropDown;
    @FindBy(name = "filter[id]")
    private WebElement findByIdField;
    @FindBy(xpath = "//button[@class='rend158-button' and contains(text(),'Show matches')]")
    private WebElement showMatchesButton;
    @FindBy(xpath = "//div[contains(@class,'search-button-by-id')]//button[@class='rend158-button']")
    private WebElement goButton;


    private String oneOptionDropDownOnlineNow = "//option[text() = '%s']";

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/search/";
    }

    public void checkIsSearchPageOpen() {
        Assert.assertTrue(isElementDisplayed(showMatchesButton));
        checkUrlWithPattern();
    }

    public void guestOpenSearchPage() {
        HomePage homePage = new HomePage(webDriver);
        homePage.openHomePage();
        openSearchPage();
        getRelativeUrl();
        Assert.assertTrue(isElementDisplayed(showMatchesButton));
    }

    public void chooseOptionAtDropDownOnlineNow(String option) {
        selectTextInDropDownByUI(onlineNowDropDownMenu, oneOptionDropDownOnlineNow, option);
    }

    public void selectWebCamCheckbox() {
        setCheckboxCondition(hasWebcamCheckBox, "check");
    }

    public void fillAllInputAtSearchForm() {
        enterTextToElement(ageFromFilter, "18");
        enterTextToElement(ageToFilter, "60");
        enterTextToElement(heightFromFilter, "140");
        enterTextToElement(heightToFilter, "185");
        enterTextToElement(weightFromFilter, "40");
        enterTextToElement(weightToFilter, "75");
    }

    public void selectRandomValueAtAllDropDowns() {
        selectRandomValueInDropDown(hairColorDropDown);
        selectRandomValueInDropDown(eyeColorDropDown);
        selectRandomValueInDropDown(ethnicityDropDown);
        selectRandomValueInDropDown(drinkDropDown);
        selectRandomValueInDropDown(smokeDropDown);
        selectRandomValueInDropDown(religionDropDown);
        selectRandomValueInDropDown(maritalStatusDropDown);
        selectRandomValueInDropDown(childrenDropDown);
        selectRandomValueInDropDown(educationDropDown);
        selectRandomValueInDropDown(countryDropDown);
    }

    public void clickAtSearchButtonAndCheckResult() {
        ViewMatchesPage viewMatchesPage = new ViewMatchesPage(webDriver);
        clickOnElement(showMatchesButton);
        viewMatchesPage.checkSearchResult();
    }

    public void findGirlById() {
        enterTextToElement(findByIdField, "1009469");
        clickOnElement(goButton);
    }
}
