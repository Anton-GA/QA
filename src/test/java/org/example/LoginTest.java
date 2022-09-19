package org.example;

import data.Constants;
import org.example.PageObject.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class LoginTest extends BaseTest {
//    LoginPage loginPage = new LoginPage(driver);

    @Test
    public void logIn() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginField("agorbatov@codeitup.ru").fillPasswordField("Ghbphfr1234");
    }

    @Test
    public void incorrectLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginField("йцуке").loginErrorMessage(Constants.ruLoginErrorMessage);
    }

    @Test
    public void nonExistentLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginField("bghnhnhh").loginErrorMessage(Constants.enLoginErrorMessage);
    }

    @Test
    public void incorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginField("agorbatov@codeitup.ru");
        loginPage.fillPasswordField("qwerty").incorrectPasswordMessage(Constants.incorrectPasswordMessage);
    }

    @Test
    public void returnBack() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginField("agorbatov@codeitup.ru");
        loginPage.loginValue(Constants.mail);

    }

//
//    @Test
//    public void nonExistentLogin() {
//        WebElement element = driver.findElement(By.id("passp-field-login"));
//        element.sendKeys("йцуке");
//        driver.findElement(By.id("passp:sign-in")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Assert.assertEquals(driver.findElement(By.id("field:input-login:hint")).getText(), "Такой логин не подойдет");
//    }
//
//    @Test
//    public void incorrectLogin() {
//        WebElement element = driver.findElement(By.id("passp-field-login"));
//        element.sendKeys("bnmnbmgjgj");
//        driver.findElement(By.id("passp:sign-in")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        Assert.assertEquals(driver.findElement(By.id("field:input-login:hint"))
//                .getText(),"Такого аккаунта нет");
//    }
//
//    @Test
//    public void incorrectPassword() {
//        WebElement element = driver.findElement(By.id("passp-field-login"));
//        element.sendKeys("agorbatov@codeitup.ru");
//        driver.findElement(By.id("passp:sign-in")).click();
//        driver.findElement(By.id("passp-field-passwd")).sendKeys("qwerty", Keys.ENTER);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        Assert.assertEquals(driver.findElement(By.id("field:input-passwd:hint"))
//                .getText(), "Неверный пароль");
//    }
//
//    @Test
//    public void returnBack() {
//////        Actions actions = new Actions(driver);
//        WebElement element = driver.findElement(By.id("passp-field-login"));
//        element.sendKeys("agorbatov@codeitup.ru");
//        driver.findElement(By.id("passp:sign-in")).click();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.findElement(By.className("PreviousStepButton")).click();
////
//////        actions.moveToElement(driver.findElement(By.cssSelector("[class='PreviousStepButton']")))
//////                .click();
//////                .build()
//////                .perform();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        Assert.assertEquals(driver.findElement(By.id("passp-field-login"))
//                .getAttribute("value"), "agorbatov@codeitup.ru");
//    }
}


