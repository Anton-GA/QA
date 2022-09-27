package ru.kinopoisk;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.webdriver.Url;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Objects;

import static com.codeborne.selenide.WebDriverRunner.url;
import static java.lang.Thread.sleep;

public class BaseTest {
    private final String url = "https://www.kinopoisk.ru/";


    @BeforeMethod
    public void setUp() throws InterruptedException {
        Configuration.browserSize = "1920x1080";
        Selenide.open(url);
        while(!Objects.equals(url(), url)) {
            sleep(5000);
            break;
        }
    }


}