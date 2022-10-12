package ru.kinopoisk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.webdriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public abstract class BaseTest {
    private final String url = "https://www.kinopoisk.ru/";

    @BeforeClass
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 4_000;
        String urlKinopoisk = "https://www.kinopoisk.ru/showcaptcha";
        Selenide.open(url);
//        webdriver().shouldHave(url(url), Duration.ofSeconds(20));
        webdriver().shouldNotHave((urlContaining(urlKinopoisk)), Duration.ofSeconds(100));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
    }
}
