package ru.kinopoisk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.RegexKinopoisk;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;


public class TodayInCinemaPage extends BaseTest{

    private final SelenideElement header = $(By
            .cssSelector(("div > section > h3 > a[href='/afisha/new/city']")));
    private final SelenideElement blockToday = $(By
            .cssSelector("#today-in-cinema-block+div"));
    private final SelenideElement displayCarousel = $(By
            .cssSelector("#today-in-cinema-block+div > section > h3+div"));


    public TodayInCinemaPage assertBlockTodayInCinema() {
        blockToday.shouldBe(Condition.visible);
        return this;
    }

    public TodayInCinemaPage assertHeaders() {
        blockToday.scrollTo();
        Assert.assertEquals(header.getText(), "Билеты в кино");
        return this;
    }

    public TodayInCinemaPage carouselIsDisplayed() {
        displayCarousel.shouldBe(Condition.visible);
        return this;
    }

    public TodayInCinemaPage regexTodayInCinemaBlock() {
        CommonSteps.regexAssertHref(RegexKinopoisk.regexUrlTodayInCinema, header);
        return this;
    }

    public TodayInCinemaPage regexRedirectTodayInCinema() {
        CommonSteps.regexAssertUrl(RegexKinopoisk.checkOpenUrlToday, header);
        return this;
    }
}
