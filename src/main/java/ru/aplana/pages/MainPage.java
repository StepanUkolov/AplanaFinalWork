package ru.aplana.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aplana.steps.BaseSteps;
import ru.aplana.util.Product;

import java.util.List;

public class MainPage extends BasePageObject {
    public MainPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }



    @FindBy(xpath = "//button[@aria-label='Закрыть сообщение']")
    private WebElement cookieButton;

    @FindBy(xpath = "//a[@class='exponea-banner exponea-popup-banner exponea-animate']/span/span")
    private WebElement mainPageBanner;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(text(),'Цена')]/..//div/label[text()='до']/..//input[@class='ui-av9 ui-av4']")
    private WebElement priceLimit;

    @FindBy(xpath = "//a[@data-widget='cart']")
    private WebElement cartPageButton;

    @FindBy(xpath = "//div[contains(text(),'Бренды')]/..//span[contains(text(),'Посмотреть все')]")
    private WebElement allBrands;


    public void closeCookieWindow() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
        cookieButton.click();
    }

    public void closeMainPageBanner() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(mainPageBanner));
        mainPageBanner.click();
    }

    public void setSearchField(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchField.clear();
        searchField.sendKeys(value);
        searchField.sendKeys(Keys.ENTER);
    }

    public void setPriceLimit(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Пробовал все возможные варианты, работает только так
        priceLimit.sendKeys(Keys.SHIFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT);
        priceLimit.sendKeys(value);
        priceLimit.sendKeys(Keys.ENTER);
    }

    public void setCheckBox(String value) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = BaseSteps.getDriver().findElement(By.xpath("//div/label//span[text()='" + value + "']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void viewAllBrands() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allBrands.click();
    }

    public void addToCartOdd(int count) {
        BaseSteps.productsList.clear();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int arraySize = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//button[@qa-id='tile-buy-button']")).size();
        Assert.assertTrue("Нет нужного количества элементов, всего: " + arraySize, arraySize > count * 2);

        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<WebElement> productBuyButtons = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//button[@qa-id='tile-buy-button']"));

            wait.until(ExpectedConditions.elementToBeClickable(productBuyButtons.get(i)));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Actions(BaseSteps.getDriver()).moveToElement(productBuyButtons.get(i)).click().perform();

            String productName = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//a[@data-test-id='tile-name']")).get(i * 2).getText();
            String productPrice = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//span[@data-test-id='tile-price']")).get(i * 2).getText().replaceAll("\\D", "");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Product product = new Product(productName, productPrice);

            BaseSteps.addToProductsList(product);
        }


    }

    public void addToCartEven() {
        BaseSteps.productsList.clear();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int arraySize = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//button[@qa-id='tile-buy-button']")).size();

        for (int i = 1; i < arraySize / 2; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<WebElement> productBuyButtons = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//button[@qa-id='tile-buy-button']"));

            wait.until(ExpectedConditions.elementToBeClickable(productBuyButtons.get(i)));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Actions(BaseSteps.getDriver()).moveToElement(productBuyButtons.get(i)).click().perform();

            String productName = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//a[@data-test-id='tile-name']")).get((i - 1) * 2 + 1).getText();
            String productPrice = BaseSteps.getDriver().findElements(By.xpath("//div[@data-v-41940272 and @data-widget='searchResultsV2']/div/div//span[@data-test-id='tile-price']")).get((i - 1) * 2 + 1).getText().replaceAll("\\D", "");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Product product = new Product(productName, productPrice);

            BaseSteps.addToProductsList(product);

        }

    }

    public void goToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(cartPageButton));
        cartPageButton.click();
    }


}
