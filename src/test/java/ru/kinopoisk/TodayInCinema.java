package ru.kinopoisk;

import org.testng.annotations.Test;

public class TodayInCinema extends BaseTest{

    TodayInCinemaPage todayInCinemaPage = new TodayInCinemaPage();

    @Test
    public void generalChecks() {
    todayInCinemaPage
            .assertBlockTodayInCinema()
            .assertHeaders()
            .regexTodayInCinemaBlock()
            .carouselIsDisplayed()
            .regexRedirectTodayInCinema();
    }
}
