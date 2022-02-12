package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;


abstract public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    WebDriverWait webDriverWait10, webDriverWait15, webDriverWait30;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl = configProperties.base_url();

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_DFFAULT_WAIT());
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
        webDriverWait30 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT());
    }

    abstract String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Invalid page",
                baseUrl + getRelativeUrl(),
                webDriver.getCurrentUrl());
    }

    protected void checkUrlWithPattern() {
        Assert.assertThat("Invalid page",
                webDriver.getCurrentUrl(),
                containsString(baseUrl + getRelativeUrl()));
    }

    protected void closeCookiesPopUp() {
        try {
            if (new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup_plugin_info-accept-cookies__text"))).isDisplayed()) {
                webDriver.findElement(By.className("popup_plugin_info-accept-cookies__text")).click();
            }
        } catch (TimeoutException e) {
        }
    }

    protected void enterTextToElement(WebElement element, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputted ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement element) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void checkTextFromElement(String text, WebElement element) {
        try {
            Assert.assertEquals(text, element.getText());
            logger.info("Text '" + text + "' is correct");
        } catch (Exception e) {
            logger.error("Text '" + text + "' is not correct" + e);
            Assert.fail("Text '" + text + "' is not correct" + e);
        }
    }

    protected void checkTextContainAtElement(String text, WebElement element) {
        try {
            Assert.assertTrue(webDriverWait10.until(ExpectedConditions.textToBePresentInElement(element, text)));
            logger.info("Text '" + text + "' is correct");
        } catch (Exception e) {
            logger.error("Text '" + text + "' is not correct" + e);
            Assert.fail("Text '" + text + "' is not correct" + e);
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectTextInDropDownByUI(WebElement dropDown, String option, String text) {
        try {
            clickOnElement(dropDown);
            WebElement onePersonalOption = webDriver.findElement(By.xpath(String.format(option, text)));
            onePersonalOption.click();
            logger.info(text + " was selected at DropDown menu");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectRandomValueInDropDown(WebElement element) {
        try {
            Select selectElement = new Select(element);
            List<WebElement> validSelectOptions = selectElement
                    .getOptions()
                    .stream()
                    .filter(p -> !p.getAttribute("value").equals(""))
                    .collect(Collectors.toList());
            Random rand = new Random();
            WebElement chosenElement = validSelectOptions.get(rand.nextInt(validSelectOptions.size()));
            selectElement.selectByValue(chosenElement.getAttribute("value"));
            logger.info(chosenElement + " value was selected in DD");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void setCheckboxCondition(WebElement element, String checkboxCondition) {
        boolean currentElementCondition = element.isSelected();
        if (checkboxCondition == "check" || checkboxCondition == "uncheck") {
            switch (checkboxCondition) {
                case ("check"):
                    if (currentElementCondition) {
                        logger.info("Checkbox condition is correct");
                    } else {
                        clickOnElement(element);
                        logger.info("Checkbox condition was changed on condition - " + checkboxCondition);
                    }
                    break;
                case ("uncheck"):
                    if (!currentElementCondition) {
                        logger.info("Checkbox condition is correct");
                    } else {
                        clickOnElement(element);
                        logger.info("Checkbox condition was changed on condition - " + checkboxCondition);
                    }
                    break;
            }
        } else {
            logger.error("Wrong 'checkboxCondition' - " + checkboxCondition + ". Should be: 'check' or 'uncheck'");
            Assert.fail("Wrong 'checkboxCondition' - " + checkboxCondition + ". Should be: 'check' or 'uncheck'");
        }
    }

    protected void checkOneOfTwoElementsPresent(WebElement element1, WebElement element2) {
        List<WebElement> webElementList = new ArrayList<>();
        webElementList.add(element1);
        webElementList.add(element2);
        boolean isDisplayed = true;
        for (WebElement element : webElementList) {
            try {
                new WebDriverWait(webDriver, 2).until(ExpectedConditions.visibilityOf(element));
                isDisplayed = true;
                logger.info("Element was found");
                break;
            } catch (WebDriverException we) {
                isDisplayed = false;
                continue;
            }
        }
        Assert.assertTrue("Failed at step checking present one of two elements ", isDisplayed);
    }

    protected void acceptAlert() {
        try {
            webDriverWait10.until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
}
