package org.example.PageObject;

import org.checkerframework.checker.signature.qual.ClassGetName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(id = "passp:sign-in")
    private WebElement enterButton;

    @FindBy(id = "field:input-login:hint")
    private WebElement errorAccountMessage;

    @FindBy(id = "field:input-passwd:hint")
    private WebElement errorPasswordMessage;

    @FindBy(className = "PreviousStepButton")
    private WebElement previousStepButton;

//    @FindBy(value = "login")
//    private WebElement valueOfLoginField;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private String getTextField(WebElement element) {
//        element.getText();
        return element.getText();
    }

    private LoginPage clickField(WebElement element) {
        element.click();
        return this;
    }

    private LoginPage fillField(WebElement element, String text) {
        element.sendKeys(text);
        return this;
    }

    private LoginPage getAttribute(WebElement element) {
        element.getAttribute("value");
        return this;
    }

    private void clickEnterButton() {
        enterButton.click();
    }

    private void clickPreviousButton() {
        previousStepButton.click();
    }

    public LoginPage loginValue(String text) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickPreviousButton();
        Assert.assertEquals(driver.findElement(By.id("passp-field-login"))
                .getAttribute("value"), text);
        return this;
    }

    public LoginPage loginErrorMessage(String text) {
        String mess = getTextField(errorAccountMessage);
        Assert.assertEquals(mess, text);
        return this;
    }

    public LoginPage incorrectPasswordMessage(String text) {
        String mess = getTextField(errorPasswordMessage);
        Assert.assertEquals(mess, text);
        return this;
    }

    public LoginPage fillLoginField(String text) {
        clickField(loginField);
        fillField(loginField, text);
        clickEnterButton();
        return this;
    }

    public LoginPage fillPasswordField(String text) {
        clickField(passwordField);
        fillField(passwordField, text);
        clickEnterButton();
        return this;
    }
}
