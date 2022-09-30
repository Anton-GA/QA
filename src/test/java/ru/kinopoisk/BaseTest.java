package ru.kinopoisk;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.webdriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import static com.codeborne.selenide.WebDriverConditions.url;
import static java.lang.Thread.sleep;

public abstract class BaseTest {
    private final String url = "https://www.kinopoisk.ru/?";


    @BeforeClass
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Selenide.open(url);
        webdriver().shouldHave(url(url), Duration.ofSeconds(100));


//        label:{
//            while(!Objects.equals(url(), url)) {
//                sleep(50000);
//            }
//            if (Objects.equals(url(), url)) {
//                sleep(500);
//            }
//        }

    }

}