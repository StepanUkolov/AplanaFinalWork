package ru.aplana.steps;

import io.cucumber.java.ru.Когда;
import io.qameta.allure.Step;
import ru.aplana.pages.MainPage;

public class MainPageSteps {
    MainPage mainPage = new MainPage();

    @Когда("^Закрываем всплывающее окно$")
    public void closeCookieWindow() {
        mainPage.closeCookieWindow();
    }

    @Когда("^Закрываем баннер с рекламой$")
    public void closeMainPageBanner() {
        mainPage.closeMainPageBanner();
    }

    @Step("Поисковое поле заполняется значением {0}")
    public void searchProduct(String value) {
        mainPage.setSearchField(value);
    }

    @Когда("^Ограничиваем цену до (.*)₽$")
    public void priceLimit(String value) {
        mainPage.setPriceLimit(value);
    }

    @Когда("^Нажимаем кнопку посмотреть все бренды$")
    public void viewAllBrands() {
        mainPage.viewAllBrands();
    }

    @Step("Выбираем опцию: {0}")
    public void checkBox(String value) {
        mainPage.setCheckBox(value);
    }

    @Step("Добавляем товары в корзину")
    public void addToCart(String option, int count) {
        if (option.equals("odd")){
            mainPage.addToCartOdd(count);
        }else if (option.equals("even")){
            mainPage.addToCartEven();
        }
    }

    @Когда("^Переходим в корзину$")
    public void goToCartPage() {
        mainPage.goToCartPage();
    }




}

