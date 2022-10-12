package ru.kinopoisk.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.kinopoisk.BaseTest;
import ru.kinopoisk.pageobject.TodayInCinemaPage;

import static com.codeborne.selenide.Selenide.page;

public class TodayInCinema extends BaseTest {


    TodayInCinemaPage todayInCinemaPage = page(TodayInCinemaPage.class);


    @BeforeClass
    public void scroll() {
        todayInCinemaPage.scrollToBlock();
    }

    @Test
    @Description("Проверка отображения блока Билеты в кино, корректность заголовка, корректность ссылки," +
            "отображение карусели, корректность ссылки при переходе в Билеты в кино")
    public void generalChecks() {
    todayInCinemaPage
            .assertBlockTodayInCinema()
            .assertHeaders()
            .checkHrefTodayInCinemaBlock()
            .carouselIsDisplayed()
            .checkURLRedirectTodayInCinema();
    }

    @Test(invocationCount = 1)
    @Flaky
    @Description("Отображение сниппетов, название фльмов у сниппетов, год и жанр фильмов, ссылки у сниппетов, " +
            "отображение 'В главной роли' и 'Режиссер', рейтинг у сниппетов, отбражение кнопки влево в карусели")
    public void checkCarouselElements() {
        todayInCinemaPage
//                .checkRightAndLeftButtons()
                .displayedSnippets()
                .checkFilmTitle()
                .checkYearAndGenre()
                .checkHrefOfSnippets()
                .checkInMainRolesAndProducer()
                .checkRating()
                .checkButtonWithTickets();
    }
}
