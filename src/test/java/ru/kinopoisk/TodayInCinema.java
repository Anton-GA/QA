package ru.kinopoisk;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TodayInCinema extends BaseTest{

    TodayInCinemaPage todayInCinemaPage = new TodayInCinemaPage();

    @BeforeMethod
    public void scroll() {
        todayInCinemaPage.scrollToBlock();
    }


    /**
     * Кейс №1 - проверка отображения блока Билеты в кино, корректность заголовка,
     * корректность ссылки Билеты в кино, отображение карусели, корректность ссылки при переходе
     * по Билеты в кино
     */
    @Test
    public void generalChecks() {
    todayInCinemaPage
            .assertBlockTodayInCinema()
            .assertHeaders()
            .checkHrefTodayInCinemaBlock()
            .carouselIsDisplayed()
            .checkURLRedirectTodayInCinema();
    }

    /**
     * Кейс №2 - Отображение сниппетов, корректное название фильмов у сниппетов, корректность года и жанра фильмов
     */
    @Test
    public void checkCarouselElements() {
        todayInCinemaPage
                .displayedSnippets()
                .checkFilmTitle()
                .checkYearAndGenre();
    }
}
