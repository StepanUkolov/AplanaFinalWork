package ru.aplana.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.aplana.steps.BaseSteps;
import ru.aplana.util.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends BasePageObject{
    public CartPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'В избранное')]/../../..")
    private List<WebElement> cartProducts;

    @FindBy(xpath = "//span[contains(text(),'Ваша корзина')]/../span[contains(text(),'товар')]")
    private WebElement cartCount;

    @FindBy(xpath = "//span[contains(text(),'Удалить выбранные')]")
    private WebElement deleteProductsButton;

    public void checkCartProducts() {
        ArrayList<Product> cartProductsList = new ArrayList<>();

        for (WebElement element : cartProducts){
            String productName = element.findElement(By.xpath(".//a/span")).getText();
            String productPrice = element.findElement(By.xpath(".//div[@style='font-size: 15px; color: rgb(0, 26, 52); font-weight: bold;']/span"))
                    .getText()
                    .replaceAll("\\D","");

            cartProductsList.add(new Product(productName, productPrice));
        }
        for (Product product : BaseSteps.productsList){
            Assert.assertTrue("В корзине не хватает: " + product.getProductName() + " " + product.getProductPrice() , cartProductsList.contains(product));
        }
    }

    public void checkCartCount(String value) {
        String result = "";
        String curent = cartCount.getText();

        Pattern pattern = Pattern.compile("\\d .* •");
        Matcher matcher = pattern.matcher(curent);
        if (matcher.find()) {
            result = matcher.group();
        }

        Assert.assertEquals(value, result.replaceAll(" •", ""));

    }

    public void deleteCartProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteProductsButton));
        deleteProductsButton.click();

        WebElement deleteWindow = BaseSteps.getDriver().findElement(By.xpath("//button/div/div[contains(text(),'Удалить')]"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteWindow));
        deleteWindow.click();
    }

    public void checkEmptyCart() {
        WebElement title = BaseSteps.getDriver().findElement(By.xpath("//h1"));
        Assert.assertEquals("Корзина пуста", title.getText());
    }

}
