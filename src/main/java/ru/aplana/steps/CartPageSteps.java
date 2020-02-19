package ru.aplana.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.aplana.pages.CartPage;

public class CartPageSteps {
    CartPage cartPage = new CartPage();

    @Тогда("^Проверяем, что все добавленные ранее товары находятся в корзине$")
    public void checkCartProducts() {
        cartPage.checkCartProducts();
    }

    @Тогда("^Проверяем, что отображается текст «Ваша корзина - (.*)»$")
    public void checkCartProduct(String value) {
        cartPage.checkCartCount(value);
    }

    @Когда("^Удаляем все товары из корзины$")
    public void deleteCartProducts() {
        cartPage.deleteCartProducts();
    }

    @Тогда("^Проверяем, что корзина не содержит никаких товаров$")
    public void checkDeleteCartProducts() {
        cartPage.checkEmptyCart();
    }

}

