package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openHomePage() {
        try {
            webDriver.get(baseUrl + "/");
            checkUrl();
            closeCookiesPopUp();
            logger.info("Home Page was opened");
        } catch (Exception e) {
            logger.error("Can not open Home Page " + e);
            Assert.fail("Can not open Home Page " + e);
        }
    }


}
