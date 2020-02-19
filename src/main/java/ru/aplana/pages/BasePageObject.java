package ru.aplana.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.steps.BaseSteps;

public class BasePageObject {
    public static WebDriverWait wait;
    public static JavascriptExecutor js;

    public BasePageObject() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
        wait = new WebDriverWait(BaseSteps.getDriver(), 20);
        js = (JavascriptExecutor) BaseSteps.getDriver();
    }

}
