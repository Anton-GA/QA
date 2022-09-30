package ru.kinopoisk.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kinopoisk.BaseTest;
import ru.kinopoisk.pageobject.TodayInCinemaPage;

import static com.codeborne.selenide.Selenide.page;

public class TodayInCinema extends BaseTest {


    TodayInCinemaPage todayInCinemaPage = page(TodayInCinemaPage.class);


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
     * Кейс №2 - Отображение сниппетов, корректное название фильмов у сниппетов, корректность года и жанра фильмов,
     * корректные ссылки у каждого сниппета, отображение блоков "В главной роли" и "Режиссер",
     * корректность рейтинга у каждого сниппета, проверка отображения кнопки влево
     */
    @Test
    public void checkCarouselElements() {
        todayInCinemaPage
                .displayedSnippets()
                .checkFilmTitle()
                .checkYearAndGenre()
                .checkHrefOfSnippets()
                .checkInMainRolesAndProducer()
                .checkRating()
                .checkRightAndLeftButtons()
                .checkButtonWithTickets();
    }
}
